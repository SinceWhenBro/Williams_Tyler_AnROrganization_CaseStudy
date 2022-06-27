package com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.controllers;

import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.models.Artist;
import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/artist")
//controls artist actions
public class ArtistController {

    //auto wired artistService in order to use its methods
    @Autowired
    private ArtistService artistService;

    //home page routing for artist which return the artist index
    //use model.addAttribute in order to be able to call listArtists in Thymeleaf
    @GetMapping("/home")
    public String viewArtistsPage(Model model){

        model.addAttribute("listArtists", artistService.getAllArtists());
        return "artist_index";
    }

    //routing to new artist form
    //model.addAttribute is used to bind the Thymeleaf form data to the artist object
    @GetMapping("/showNewArtistForm")
    public String showNewArtistForm(Model model) {
        Artist artist = new Artist();
        model.addAttribute("artist", artist);
        return "new_artist";
    }

    //routing for saving new artist to db
    //ModelAttribute allows us to bind the artist info from the form to an Artist object
    @PostMapping("/saveArtist")
    public String saveArtist(@ModelAttribute("artist") Artist artist){

        //save artist to database
        artistService.saveArtist(artist);
        return "redirect:/artist/home";
    }

    //routing to update artist form
    //@Pathvariable is passing back the id of the specified artist to update
    //model.addAttribute is used to bind the Thymeleaf form data to the artist object
    @GetMapping("/showFormForArtistUpdate/{id}")
    public String showFormforArtistUpdate(@PathVariable(value ="id") long id, Model model){
        //get artist from the service
        Artist artist = artistService.getArtistById(id);
        //set artist as a model attribute to prepopulate form
        model.addAttribute("artist", artist);
        return "update_artist";
    }

    //routing to delete artist
    //@Pathvariable is passing back the id of the specified artist to delete
    //using service to delete from db
    @GetMapping("/deleteArtist/{id}")
    public String deleteArtist(@PathVariable (value ="id") long id){
        //call delete artist method
        this.artistService.deleteArtistById(id);
        return "redirect:/artist/home";
    }
}
