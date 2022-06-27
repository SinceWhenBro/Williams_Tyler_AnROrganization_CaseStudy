package com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.services;

import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.models.Role;
import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.repositories.UserRepository;
import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.models.User;
import com.example.Williams_Tyler_ArtistManagementSystem_CaseStudy.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    //auto wiring in userRepository inorder to use its methods
    @Autowired
    private UserRepository userRepository;

    //auto wiring bCrypt to encode passwords
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    //Saves a new user to the db using .save with the role of ARTIST
    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getUsername(),
                registrationDto.getEmail(), passwordEncoder.encode(registrationDto.getPassword()),
//        if(isManager == true){
//            Arrays.asList(new Role ("MANAGER"));
//        } else{
//            Arrays.asList(new Role ("ARTIST"));
//        }
                Arrays.asList(new Role("ARTIST")));
        return userRepository.save(user);
    }

    //Saves a new user to the db using .save with the role of ARTIST
    @Override
    public User saveManager(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getUsername(),
                registrationDto.getEmail(), passwordEncoder.encode(registrationDto.getPassword()),
                Arrays.asList(new Role("MANAGER")));
        return userRepository.save(user);
    }

    //Gets a user based off their username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    //Map authorities for spring security to use
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }
}
