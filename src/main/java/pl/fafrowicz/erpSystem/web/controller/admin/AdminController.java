package pl.fafrowicz.erpSystem.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.fafrowicz.erpSystem.web.dto.UserDto;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/homepage")
    public String showHomepage() {


        return "admin/homepage";
    }


    @GetMapping("/employee/add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new UserDto());
        return "employeeRegistration";
    }


}
