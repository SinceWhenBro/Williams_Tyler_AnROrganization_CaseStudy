package com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.services;

import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.models.Artist;
import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.repositories.ArtistRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.Column;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArtistServiceImplTest {

    @Autowired
    private ArtistService artistService;

    @MockBean
    private ArtistRepository artistRepository;

    @Test
    void saveArtist() {
        Artist testArtist = new Artist();
        testArtist.setId(1);
        testArtist.setArtistName("sincewhen");
        testArtist.setFirstName("tyler");
        testArtist.setLastName("williams");
        testArtist.setLabel("independent");
        testArtist.setGenre("bedroompop");
        testArtist.setPhoneNumber("1234567890");
        testArtist.setEmail("email@test.com");

        artistRepository.save(testArtist);

        artistService.saveArtist(testArtist);
        String actual = testArtist.getArtistName();
        String expected = testArtist.getArtistName();

        assertEquals(actual,expected);
    }


}