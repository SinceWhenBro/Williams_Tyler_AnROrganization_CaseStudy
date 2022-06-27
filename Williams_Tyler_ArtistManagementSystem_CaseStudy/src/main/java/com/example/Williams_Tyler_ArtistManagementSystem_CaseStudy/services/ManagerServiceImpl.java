package com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.services;

import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.models.Manager;
import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    //Gets all managers using the .findAll which is implemented by JPA Repository
    @Override
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    //Saves a new manager to the db using .save which is implemented by JPA Repository
    @Override
    public void saveManager(Manager manager) {
        this.managerRepository.save(manager);
    }

    //Gets a specific managers using getManagerById which is implemented by JPA Repository
    @Override
    public Manager getManagerById(long id) {
        Optional<Manager> optional = managerRepository.findById(id);
        Manager manager = null;
        if (optional.isPresent()) {
            manager = optional.get();
        } else {
            throw new RuntimeException("Manager not found for id: " + id);
        }
        return manager;
    }

    //Deletes a specific manager using getManagerById which is implemented by JPA Repository
    @Override
    public void deleteManagerById(long id) {
        this.managerRepository.deleteById(id);
    }
}
