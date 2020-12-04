package pl.fafrowicz.erpSystem.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.fafrowicz.erpSystem.error.TaskAlreadyExistException;
import pl.fafrowicz.erpSystem.persistence.dao.RoleRepository;
import pl.fafrowicz.erpSystem.persistence.entity.Task;
import pl.fafrowicz.erpSystem.persistence.entity.User;
import pl.fafrowicz.erpSystem.persistence.entity.UserTaskHoursBudget;
import pl.fafrowicz.erpSystem.persistence.service.TaskService;
import pl.fafrowicz.erpSystem.persistence.service.UserService;
import pl.fafrowicz.erpSystem.persistence.service.UserTaskHoursBudgetService;
import pl.fafrowicz.erpSystem.security.MyUserDetails;
import pl.fafrowicz.erpSystem.web.dto.TaskDto;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/task")
public class TaskController {
    UserService userService;
    RoleRepository roleRepository;
    TaskService taskService;
    UserTaskHoursBudgetService userTaskHoursBudgetService;

    @Autowired
    public TaskController(UserService userService, RoleRepository roleRepository, TaskService taskService, UserTaskHoursBudgetService userTaskHoursBudgetService) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.taskService = taskService;
        this.userTaskHoursBudgetService = userTaskHoursBudgetService;
    }


    @GetMapping("")
    public String tasksList(@AuthenticationPrincipal MyUserDetails user,Model model) {
        List<Task> activeTasks = this.taskService.findAllActiveTasks(user.getCompany());
        List<Task> completedTasks = this.taskService.findAllCompletedTasks(user.getCompany());
        model.addAttribute("activeTasks", activeTasks);
        model.addAttribute("completedTasks", completedTasks);

        return "admin/task/listTasks";
    }


    @GetMapping("/add")
    public String addForm(final Model model) {
        final TaskDto task = new TaskDto();
        model.addAttribute("task", task);
        return "admin/task/addTask";
    }

    @PostMapping("/add")
    public ModelAndView addFormPost(@AuthenticationPrincipal MyUserDetails adminUser, @ModelAttribute("task") @Valid TaskDto taskDto, BindingResult result) {
        ModelAndView mav = new ModelAndView("admin/task/addTask", "task", taskDto);

        if (result.hasErrors()) {
            return mav;
        }

        try {
            Task task = taskDto.ToTask();
            taskService.addNewTask(task, adminUser.getCompany());
        } catch (TaskAlreadyExistException uaeEx) {
            mav.addObject("message", "A task for that name already exists.");
            return mav;
        } catch (final RuntimeException ex) {
            System.out.println(ex.getMessage());
            mav.addObject("message", "Operation failed.");
            return mav;
        }
        return new ModelAndView("redirect:/admin/task");
    }


    @GetMapping("/edit/{taskId}")
    public String editForm(@PathVariable long taskId, Model model, @AuthenticationPrincipal MyUserDetails adminUser) {
        TaskDto taskDto = taskService.findByIdAndCompany(taskId, adminUser.getCompany()).get().toTaskDto();
        model.addAttribute("task", taskDto);
        return "admin/task/editTask";
    }

    @PostMapping("/edit/{taskId}")
    public ModelAndView editFormPost(@PathVariable long taskId, @ModelAttribute("task") @Valid TaskDto taskDto, BindingResult result, @AuthenticationPrincipal MyUserDetails adminUser) {
        ModelAndView mav = new ModelAndView("admin/task/editTask", "task", taskDto);

        if (result.hasErrors()) {
            return mav;
        }

        try {
            Task task = taskDto.ToTask();
            taskService.editTask(taskId, task, adminUser.getCompany());
        } catch (TaskAlreadyExistException uaeEx) {
            mav.addObject("message", "A task for that name already exists.");
            return mav;
        } catch (final RuntimeException ex) {
            System.out.println(ex.getMessage());
            mav.addObject("message", "Operation failed.");
            return mav;
        }
        return new ModelAndView("redirect:/admin/task/");
    }

    @GetMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable long taskId, Model model, @AuthenticationPrincipal MyUserDetails adminUser) {
        model.addAttribute("task", taskService.findByIdAndCompany(taskId, adminUser.getCompany()).get());
        return "admin/task/deleteConfirmation";
    }

    @PostMapping("/delete/{taskId}")
    public String deleteTaskPost(@PathVariable long taskId, @RequestParam String decision, @AuthenticationPrincipal MyUserDetails adminUser) {
        if ("yes".equals(decision)) {
            taskService.deleteById(taskId, adminUser.getCompany());
        }
        return "redirect:/admin/task/";
    }


    @GetMapping("/show/{id}")
    public String showDetails(@PathVariable long id, Model model, @AuthenticationPrincipal MyUserDetails adminUser) {
        Task task = taskService.findByIdAndCompany(id, adminUser.getCompany()).get();
        List<User> allEmployeesForTask = userService.findAllForTask(task.getId(), adminUser.getCompany());
        Map<Long, Short> hoursBudgetForUserForTask = userTaskHoursBudgetService.hoursBudgetPerUserForTask(id);
        int sumOfAllocatedHours = userTaskHoursBudgetService.sumAllowedHoursBudgetForTask(id);

        model.addAttribute("sumOfAllocatedHours", sumOfAllocatedHours);
        model.addAttribute("hoursBudgetForUserForTask", hoursBudgetForUserForTask);
        model.addAttribute("allEmployeesForTask", allEmployeesForTask);
        model.addAttribute("task", task);

        if (task.getHoursBudget() < sumOfAllocatedHours) {
            model.addAttribute("message", "Available hourly budget exceeded.Please correct allocated hours or edit task.");
        }
        if (task.isCompleted()) {
            return "admin/task/completedTaskDetails";
        }
        return "admin/task/taskDetails";
    }


    @GetMapping("employee/add/{taskId}")
    public String addEmployeeToTask(Model model, @PathVariable long taskId, @AuthenticationPrincipal MyUserDetails adminUser) {
        List<User> employeesToAdd = userService.findAllWithoutTask(taskId, adminUser.getCompany());
        Task task = taskService.findByIdAndCompany(taskId, adminUser.getCompany()).get();

        int sumOfAllocatedHours = userTaskHoursBudgetService.sumAllowedHoursBudgetForTask(taskId);

        if (task.getHoursBudget() <= sumOfAllocatedHours) {
            model.addAttribute("message", "The entire hours budget was used.");
            return "redirect:/admin/task/show/" + taskId;
        }
        if (employeesToAdd.size() == 0) {
            model.addAttribute("message", "All employees are added to the task.");
            return "redirect:/admin/task/show/" + taskId;
        }
        model.addAttribute("employeesToAdd", employeesToAdd);
        model.addAttribute("taskName", task.getName());
        model.addAttribute("hoursBudgetToBeAllocated", (task.getHoursBudget() - sumOfAllocatedHours));
        return "admin/task/addEmployeeToTask";
    }

    @PostMapping("employee/add/{taskId}")
    public String addEmployeeToTaskPost(@PathVariable long taskId, @AuthenticationPrincipal MyUserDetails adminUser, HttpServletRequest req, Model model) {
        Task task = taskService.findByIdAndCompany(taskId, adminUser.getCompany()).get();
        List<User> employeesToAdd = userService.findAllWithoutTask(taskId, adminUser.getCompany());

        int hoursBudgetToBeAllocated = task.getHoursBudget() - userTaskHoursBudgetService.sumAllowedHoursBudgetForTask(taskId);
        int sum = 0;

        for (User employee : employeesToAdd) {
            try {
                sum += Short.parseShort(req.getParameter(employee.getLastName()));
            } catch (NumberFormatException e) {
                continue;
            }
        }

        if (sum > hoursBudgetToBeAllocated) {
            model.addAttribute("message", "Available hours budget exceeded");
            return "redirect:/admin/task/employee/add/" + taskId;
        }

        for (User employee : employeesToAdd) {
            try {
                short budgetHour = Short.parseShort(req.getParameter(employee.getLastName()));
                if (budgetHour > 0 && budgetHour <= Short.MAX_VALUE) {
                    UserTaskHoursBudget userTaskHoursBudget = new UserTaskHoursBudget();
                    userTaskHoursBudget.setUser(employee);
                    userTaskHoursBudget.setTask(task);
                    userTaskHoursBudget.setHoursBudget(budgetHour);
                    userTaskHoursBudgetService.save(userTaskHoursBudget);
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return "redirect:/admin/task/show/" + taskId;
    }


    @GetMapping("employee/edit/{taskId}/{employeeId}")
    public String editEmployeeHoursBudgetForTask(Model model, @PathVariable long taskId, @PathVariable long employeeId) {
        UserTaskHoursBudget userTaskHoursBudget = userTaskHoursBudgetService.findByUserAndTask(employeeId, taskId);
        int hoursBudgetToBeAllocated = userTaskHoursBudget.getTask().getHoursBudget() - userTaskHoursBudgetService.sumAllowedHoursBudgetForTask(taskId);

        model.addAttribute("userTaskHoursBudget", userTaskHoursBudget);
        model.addAttribute("hoursBudgetToBeAllocated", hoursBudgetToBeAllocated);
        return "admin/task/editEmployeeHoursBudgetForTask";
    }

    @PostMapping("employee/edit/{taskId}/{employeeId}")
    public String editEmployeeHoursBudgetForTaskPost(@PathVariable long taskId, @ModelAttribute("userTaskHoursBudget") UserTaskHoursBudget userTaskHoursBudget, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/task/editEmployeeHoursBudgetForTask";
        }
        userTaskHoursBudgetService.save(userTaskHoursBudget);
        return "redirect:/admin/task/show/" + taskId;
    }

    @GetMapping("/employee/delete/{taskId}/{userId}")
    public String detachEmployee(@PathVariable long userId, @PathVariable long taskId, @AuthenticationPrincipal MyUserDetails adminUser) {
        userTaskHoursBudgetService.delete(userId, taskId, adminUser.getCompany());
        return "redirect:/admin/task/show/" + taskId;
    }

    @GetMapping("/toCompleted/{taskId}")
    public String moveToCompleted(@PathVariable long taskId, @AuthenticationPrincipal MyUserDetails adminUser) {
        taskService.moveToCompleted(taskId, adminUser.getCompany());
        return "redirect:/admin/task/";
    }

    @GetMapping("/toActive/{taskId}")
    public String moveToActive(@PathVariable long taskId, @AuthenticationPrincipal MyUserDetails adminUser) {
        taskService.moveToActive(taskId, adminUser.getCompany());
        return "redirect:/admin/task/";
    }
}
