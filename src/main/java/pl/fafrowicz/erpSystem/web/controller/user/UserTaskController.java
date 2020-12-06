package pl.fafrowicz.erpSystem.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.fafrowicz.erpSystem.persistence.dao.RoleRepository;
import pl.fafrowicz.erpSystem.persistence.entity.Task;
import pl.fafrowicz.erpSystem.persistence.entity.User;
import pl.fafrowicz.erpSystem.persistence.entity.UserTaskHoursBudget;
import pl.fafrowicz.erpSystem.persistence.service.TaskService;
import pl.fafrowicz.erpSystem.persistence.service.UserService;
import pl.fafrowicz.erpSystem.persistence.service.UserTaskHoursBudgetService;
import pl.fafrowicz.erpSystem.security.MyUserDetails;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user/task")
public class UserTaskController {
    UserService userService;
    RoleRepository roleRepository;
    TaskService taskService;
    UserTaskHoursBudgetService userTaskHoursBudgetService;

    @Autowired
    public UserTaskController(UserService userService, RoleRepository roleRepository, TaskService taskService, UserTaskHoursBudgetService userTaskHoursBudgetService) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.taskService = taskService;
        this.userTaskHoursBudgetService = userTaskHoursBudgetService;
    }


    @GetMapping("")
    public String showTasks(Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
        User user = userService.findUserByIdAndCompany(userDetails.getId(), userDetails.getCompany()).get();
        List<Task> userActiveTasks = taskService.findAllActiveForUser(user);
        List<Task> userCompletedTasks = taskService.findAllCompletedForUser(user);

        Map<Long, UserTaskHoursBudget> hoursBudgetForUserForTask = userTaskHoursBudgetService.hoursBudgetPerTaskForUser(user.getId());
        model.addAttribute("hoursBudgetForUserForTask", hoursBudgetForUserForTask);

        model.addAttribute("userActiveTasks", userActiveTasks);
        model.addAttribute("userCompletedTasks", userCompletedTasks);
        model.addAttribute("employee", user);
        return "user/listTasks";
    }

    @GetMapping("/show/{taskId}")
    public String showDetails(@PathVariable long taskId, Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
        Task task = taskService.findByIdAndCompany(taskId, userDetails.getCompany()).get();
        UserTaskHoursBudget userTaskHoursBudget = userTaskHoursBudgetService.findByUserAndTask(userDetails.getId(), taskId);
        model.addAttribute("userTaskHoursBudget", userTaskHoursBudget);
        model.addAttribute("task", task);
        return "user/taskDetails";
    }

}
