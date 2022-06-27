package com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.services;

import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.models.Artist;
import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.models.User;
import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.repositories.UserRepository;
import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.web.dto.UserRegistrationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    UserRepository userRepository;

    @Test
    void save() {
        User testUser = new User();
        testUser.setFirstName("tyler");
        testUser.setLastName("williams");
        testUser.setEmail("test@email.com");
        testUser.setPassword("pass");
        testUser.setUsername("sincewhen");

        userRepository.save(testUser);


        String actual = testUser.getUsername();
        String expected = testUser.getUsername();

        assertEquals(actual,expected);
    }
}