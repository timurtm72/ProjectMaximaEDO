package ru.maxima.school.projectmaximaedo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.maxima.school.projectmaximaedo.model.User;

import ru.maxima.school.projectmaximaedo.repositories.UserRepository;

import java.util.Collections;
import java.util.Optional;



@Component
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepo;
    @Autowired
    public MyUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> userRes = userRepo.findByName(name);
        if(userRes.isEmpty())
            throw new UsernameNotFoundException("Не найден пользователь = " + name);
        User user = userRes.get();
        return new org.springframework.security.core.userdetails.User(
                name,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }
}
