package pl.fafrowicz.erpSystem.web.controller.admin;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.fafrowicz.erpSystem.error.UserAlreadyExistException;
import pl.fafrowicz.erpSystem.persistence.dao.RoleRepository;
import pl.fafrowicz.erpSystem.persistence.entity.User;
import pl.fafrowicz.erpSystem.persistence.service.UserService;
import pl.fafrowicz.erpSystem.security.MyUserDetails;
import pl.fafrowicz.erpSystem.web.dto.UserDto;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/employee")
public class EmployeeController {
    UserService userService;
    RoleRepository roleRepository;


    public EmployeeController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @ModelAttribute("employees")
    public Collection<UserDto> employees(@AuthenticationPrincipal MyUserDetails user) {
        List<User> employees = this.userService.findAllEmployees(user.getCompany(), roleRepository.findByName("ROLE_USER"));
        List<UserDto> employeesDto = employees.stream()
                .map(e -> e.castToDto())
                .collect(Collectors.toList());
        return employeesDto;
    }

    @GetMapping("")
    public String employeeList() {
        return "admin/listEmployee";
    }


    @GetMapping("/register")
    public String showRegistrationForm(final Model model) {
        final UserDto userDto = new UserDto();
        model.addAttribute("employee", userDto);
        return "admin/employeeRegistration";
    }


    @PostMapping("/register")
    public ModelAndView registerUserAccount(@AuthenticationPrincipal MyUserDetails adminUser, @ModelAttribute("employee") @Valid UserDto userDto, BindingResult result) {
        ModelAndView mav = new ModelAndView("admin/employeeRegistration", "employee", userDto);

        if (result.hasErrors()) {
            return mav;
        }

        try {
            userService.registerNewEmployeeAcount(userDto, adminUser.getCompany());
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
}
