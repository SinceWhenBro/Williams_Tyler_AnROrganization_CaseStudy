package com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.web;

import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.services.UserService;
import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    //@Modelattribute is binding a method parameter or method return value to a named model attribute
    //In this case binding it to "user"
    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    //Routes to "registration"
    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    //Post request to register user account then route user to new page determined by their registration success
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto, Model model) {
        boolean isManager = false;
        model.addAttribute("isManager", isManager);
        if (isManager == true) {
            registrationDto.setRole("MANAGER");
            userService.saveManager(registrationDto);
        } else {
            registrationDto.setRole("ARTIST");
            userService.save(registrationDto);
        }
        return "redirect:/registration?success";
    }
}
