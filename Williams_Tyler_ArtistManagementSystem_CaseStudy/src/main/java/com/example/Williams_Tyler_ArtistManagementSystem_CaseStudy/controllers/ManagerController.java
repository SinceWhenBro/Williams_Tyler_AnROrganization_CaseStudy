package com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.controllers;

import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.models.Manager;
import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manager")
//controls manager actions
public class ManagerController {

    //auto wired managerService in order to use its methods
    @Autowired
    private ManagerService managerService;

    //home page routing for manager which return the manager index
    //use model.addAttribute in order to be able to call listManagers in Thymeleaf
    @GetMapping("/home")
    public String viewManagersPage(Model model) {
        model.addAttribute("listManagers", managerService.getAllManagers());
        return "manager_index";
    }

    //routing to new manager form
    //model.addAttribute is used to bind the Thymeleaf form data to the manager object
    @GetMapping("/showNewManagerForm")
    public String showNewManagerForm(Model model) {
        Manager manager = new Manager();
        model.addAttribute("manager", manager);
        return "new_manager";
    }

    //routing for saving new artist to db
    //ModelAttribute allows us to bind the artist info from the form to a Manager object
    @PostMapping("/saveManager")
    public String saveManager(@ModelAttribute("manager") Manager manager) {
        //save manager to database
        managerService.saveManager(manager);
        return "redirect:/manager/home";
    }

    //routing to update artist form
    //@Pathvariable is passing back the id of the specified manager to update
    //model.addAttribute is used to bind the Thymeleaf form data to the manager object
    @GetMapping("/showFormForManagerUpdate/{id}")
    public String showFormForManagerUpdate(@PathVariable(value = "id") long id, Model model) {
        //get artist from the service
        Manager manager = managerService.getManagerById(id);

        //set artist as a model attribute to prepopulate form
        model.addAttribute("manager", manager);
        return "update_manager";
    }

    //routing to delete manager
    //@Pathvariable is passing back the id of the specified manager to delete
    //using service to delete from db
    @GetMapping("/deleteManager/{id}")
    public String deleteManager(@PathVariable(value = "id") long id) {
        //call delete artist method
        this.managerService.deleteManagerById(id);
        return "redirect:/manager/home";
    }


}
