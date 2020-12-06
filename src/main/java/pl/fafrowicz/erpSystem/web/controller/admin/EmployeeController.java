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
import pl.fafrowicz.erpSystem.persistence.entity.UserTaskHoursBudget;
import pl.fafrowicz.erpSystem.persistence.service.TaskService;
import pl.fafrowicz.erpSystem.persistence.service.UserService;
import pl.fafrowicz.erpSystem.persistence.service.UserTaskHoursBudgetService;
import pl.fafrowicz.erpSystem.security.MyUserDetails;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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


    @GetMapping("")
    public String employeeList(@AuthenticationPrincipal MyUserDetails user, Model model) {
        List<User> employees = this.userService.findAllEmployees(user.getCompany());
        model.addAttribute("employees", employees);
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
            mav.addObject("message", "An account for that email already exists.");
            return mav;
        } catch (final RuntimeException ex) {
            System.out.println(ex.getMessage());
            mav.addObject("message", "Registration failed.");
            return mav;
        }
        return new ModelAndView("redirect:/admin/employee");
    }

    @GetMapping("/show/{id}")
    public String showDetails(@PathVariable long id, Model model, @AuthenticationPrincipal MyUserDetails adminUser) {
        User user = userService.findUserByIdAndCompany(id, adminUser.getCompany()).get();
        List<Task> userActiveTasks = taskService.findAllActiveForUser(user);
        List<Task> userCompletedTasks = taskService.findAllCompletedForUser(user);

        Map<Long, UserTaskHoursBudget> hoursBudgetForUserForTask = userTaskHoursBudgetService.hoursBudgetPerTaskForUser(id);
        model.addAttribute("hoursBudgetForUserForTask", hoursBudgetForUserForTask);

        model.addAttribute("userActiveTasks", userActiveTasks);
        model.addAttribute("userCompletedTasks", userCompletedTasks);
        model.addAttribute("employee", user);
        return "admin/employee/employeeDetails";
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable long userId, Model model, @AuthenticationPrincipal MyUserDetails adminUser) {
        model.addAttribute("employee", userService.findUserByIdAndCompany(userId, adminUser.getCompany()).get());
        return "admin/employee/deleteConfirmation";
    }

    @PostMapping("/delete/{userId}")
    public String deleteUserPost(@PathVariable long userId, @RequestParam String decision, @AuthenticationPrincipal MyUserDetails adminUser) {
        if ("yes".equals(decision)) {
            userService.deleteById(userId, adminUser.getCompany());
        }
        return "redirect:/admin/employee";
    }


    @GetMapping("/edit/{userId}")
    public String showEditForm(@PathVariable long userId, final Model model, @AuthenticationPrincipal MyUserDetails adminUser) {
        final User user = userService.findUserByIdAndCompany(userId, adminUser.getCompany()).get();
        model.addAttribute("employee", user);
        return "admin/employee/editEmployee";
    }

    @PostMapping("/edit/{userId}")
    public ModelAndView editEmployeeAccount(@PathVariable long userId, @ModelAttribute("employee") @Valid User userToEdit, BindingResult result, @AuthenticationPrincipal MyUserDetails adminUser) {
        ModelAndView mav = new ModelAndView("admin/employee/editEmployee", "employee", userToEdit);

        if (result.hasErrors()) {
            return mav;
        }

        try {
            userService.editEmployeeAccount(userId, userToEdit, adminUser.getCompany());
        } catch (UserAlreadyExistException uaeEx) {
            mav.addObject("message", "An account for that email already exists.");
            return mav;
        } catch (final RuntimeException ex) {
            System.out.println(ex.getMessage());
            mav.addObject("message", "Operation failed.");
            return mav;
        }
        return new ModelAndView("redirect:/admin/employee");
    }

    @GetMapping("/task/delete/{userId}/{taskId}")
    public String detachTask(@PathVariable long userId, @PathVariable long taskId, @AuthenticationPrincipal MyUserDetails adminUser) {
        userTaskHoursBudgetService.delete(userId, taskId, adminUser.getCompany());
        return "redirect:/admin/employee/show/" + userId;
    }


}
