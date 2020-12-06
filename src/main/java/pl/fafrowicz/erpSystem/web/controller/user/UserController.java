package pl.fafrowicz.erpSystem.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.fafrowicz.erpSystem.persistence.entity.Task;
import pl.fafrowicz.erpSystem.persistence.service.TaskService;
import pl.fafrowicz.erpSystem.persistence.service.UserService;
import pl.fafrowicz.erpSystem.security.MyUserDetails;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    TaskService taskService;
    UserService userService;

    @Autowired
    public UserController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }


    @GetMapping("/homepage")
    public String showHomepage(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {
        List<Task> tasks = taskService.findTopThreeEarliestDeadlineForUserId(userDetails.getId());
        model.addAttribute("tasks", tasks);
        return "user/homepage";
    }

}
