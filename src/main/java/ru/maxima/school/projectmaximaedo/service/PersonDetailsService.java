package ru.maxima.school.projectmaximaedo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.maxima.school.projectmaximaedo.serviceImpl.UserServiceImpl;

@Service
public class PersonDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserServiceImpl userService;

    @Autowired
    public PersonDetailsService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//        User person = userService.findByName(name);
//
//        if (person == null || person.isRemoved() == true)
//            throw new UsernameNotFoundException("User not found!");
//
//        return new UserDetails();
        return null;
  }
}
