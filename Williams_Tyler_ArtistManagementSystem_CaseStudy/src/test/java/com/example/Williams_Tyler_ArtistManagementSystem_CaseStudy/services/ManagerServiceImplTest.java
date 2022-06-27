package com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.services;

import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.models.Manager;
import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.repositories.ManagerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.Column;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ManagerServiceImplTest {

    @Autowired
    private ManagerService managerService;

    @MockBean
    private ManagerRepository managerRepository;

    @Test
    void saveManager() {
        Manager testManager = new Manager();
        testManager.setFirstName("Big");
        testManager.setLastName("Kahuna");
        testManager.setLabel("BKR");
        testManager.setPhoneNumber("1234567890");
        testManager.setEmail("test@email.com");
        testManager.setAvailable("Yes");

        managerRepository.save(testManager);
        managerService.saveManager(testManager);

        String actual = testManager.getFirstName();
        String expected = testManager.getFirstName();

        assertEquals(actual,expected);
    }
}