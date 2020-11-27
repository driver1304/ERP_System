package pl.fafrowicz.erpSystem.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.fafrowicz.erpSystem.error.UserAlreadyExistException;
import pl.fafrowicz.erpSystem.persistence.entity.Company;
import pl.fafrowicz.erpSystem.persistence.entity.User;
import pl.fafrowicz.erpSystem.persistence.service.UserService;


import javax.validation.Valid;

@Controller
public class RegistrationController {
    UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(final Model model) {
        final User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }


    @PostMapping("/registration")
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid User user, BindingResult result) {
        ModelAndView mav = new ModelAndView("registration", "user", user);

        if (result.hasErrors()) {
            return mav;
        }


        try {
            userService.registerNewAdminAccount(user);
        } catch (UserAlreadyExistException uaeEx) {
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        } catch (
                final RuntimeException ex) {
            System.out.println(ex.getMessage());
            mav.addObject("message", "Registration failed.");
            return mav;
        }
        return new ModelAndView("successRegister", "user", user);
    }
}
