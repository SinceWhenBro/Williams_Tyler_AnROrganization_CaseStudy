package com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.services;

import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.models.Artist;
import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    //Auto wiring in the repository I want to use
    @Autowired
    private ArtistRepository artistRepository;

    //Gets all artists using the .findAll which is implemented by JPA Repository
    @Override
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    //Saves a new artist to the db using .save which is implemented by JPA Repository
    @Override
    public void saveArtist(Artist artist) {
        this.artistRepository.save(artist);
    }

    //Gets a specific artists using getArtistById which is implemented by JPA Repository
    @Override
    public Artist getArtistById(long id) {
        Optional<Artist> optional = artistRepository.findById(id);
        Artist artist = null;
        if (optional.isPresent()) {
            artist = optional.get();
        } else {
            throw new RuntimeException("Artist not found for id: " + id);
        }
        return artist;
    }

    //Deletes a specific artists using getArtistById which is implemented by JPA Repository
    @Override
    public void deleteArtistById(long id) {
        this.artistRepository.deleteById(id);
    }
}
