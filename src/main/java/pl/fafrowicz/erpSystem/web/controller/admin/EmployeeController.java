package pl.fafrowicz.erpSystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.fafrowicz.erpSystem.error.UserAlreadyExistException;
import pl.fafrowicz.erpSystem.persistence.dao.RoleRepository;
import pl.fafrowicz.erpSystem.persistence.entity.Task;
import pl.fafrowicz.erpSystem.persistence.entity.User;
import pl.fafrowicz.erpSystem.persistence.service.TaskService;
import pl.fafrowicz.erpSystem.persistence.service.UserService;
import pl.fafrowicz.erpSystem.persistence.service.UserTaskHoursBudgetService;
import pl.fafrowicz.erpSystem.security.MyUserDetails;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/admin/employee")
public class EmployeeController {
    UserService userService;
    RoleRepository roleRepository;
    TaskService taskService;
    UserTaskHoursBudgetService userTaskHoursBudgetService;

    @Autowired
    public EmployeeController(UserService userService, RoleRepository roleRepository, TaskService taskService, UserTaskHoursBudgetService userTaskHoursBudgetService) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.taskService = taskService;
        this.userTaskHoursBudgetService = userTaskHoursBudgetService;
    }

    @ModelAttribute("employees")
    public Collection<User> employees(@AuthenticationPrincipal MyUserDetails user) {
        List<User> employees = this.userService.findAllEmployees(user.getCompany(), roleRepository.findByName("ROLE_USER"));
        return employees;
    }

    @GetMapping("")
    public String employeeList() {
        return "admin/employee/listEmployee";
    }


    @GetMapping("/register")
    public String showRegistrationForm(final Model model) {
        final User user = new User();
        model.addAttribute("employee", user);
        return "admin/employee/employeeRegistration";
    }


    @PostMapping("/register")
    public ModelAndView registerUserAccount(@AuthenticationPrincipal MyUserDetails adminUser, @ModelAttribute("employee") @Valid User user, BindingResult result) {
        ModelAndView mav = new ModelAndView("admin/employee/employeeRegistration", "employee", user);

        if (result.hasErrors()) {
            return mav;
        }

        try {
            userService.registerNewEmployeeAcount(user, adminUser.getCompany());
        } catch (UserAlreadyExistException uaeEx) {
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        } catch (final RuntimeException ex) {
            System.out.println(ex.getMessage());
            mav.addObject("message", "Registration failed.");
            return mav;
        }
        return new ModelAndView("redirect:/admin/employee");
    }

    @GetMapping("/show/{id}")
    public String showDetails(@PathVariable long id, Model model) {
        User user = userService.findUserById(id).get();
        List<Task> userActiveTasks = taskService.findAllActiveForUser(user);
        List<Task> userCompletedTasks = taskService.findAllCompletedForUser(user);
        model.addAttribute("userActiveTasks", userActiveTasks);
        model.addAttribute("userCompletedTasks", userCompletedTasks);
        model.addAttribute("employee", user);
        return "admin/employee/employeeDetails";
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable long userId) {
        userService.deleteById(userId);
        return "redirect:/admin/employee";
    }


    @GetMapping("/edit/{userId}")
    public String showEditForm(@PathVariable long userId, final Model model) {
        final User user = userService.findUserById(userId).get();
        model.addAttribute("employee", user);
        return "admin/employee/editEmployee";
    }

    @PostMapping("/edit/{userId}")
    public ModelAndView editEmployeeAccount(@PathVariable long userId, @ModelAttribute("employee") @Valid User userToEdit, BindingResult result) {
        ModelAndView mav = new ModelAndView("admin/employee/employeeRegistration", "employee", userToEdit);

        if (result.hasErrors()) {
            return mav;
        }

        try {
            userService.editEmployeeAccount(userId, userToEdit);
        } catch (final RuntimeException ex) {
            System.out.println(ex.getMessage());
            mav.addObject("message", "Operation failed.");
            return mav;
        }
        return new ModelAndView("redirect:/admin/employee");
    }

    @GetMapping("/task/delete/{userId}/{taskId}")
    public String deleteEmployeeTask(@PathVariable long userId, @PathVariable long taskId) {
        userTaskHoursBudgetService.delete(userId, taskId);
        return "redirect:/admin/employee/show/" + userId;
    }


}
