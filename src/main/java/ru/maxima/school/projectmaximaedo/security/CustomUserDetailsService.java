package ru.maxima.school.projectmaximaedo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.maxima.school.projectmaximaedo.model.User;
import ru.maxima.school.projectmaximaedo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public CustomUserDetails loadUserByUsername(String login) {
       User user = userRepository.findUserByLoginAndIsRemovedIsFalse(login).orElseThrow(
                () -> new UsernameNotFoundException(
                        "Пользователь с логином <" + login + "> не найден"));
        return new CustomUserDetails(user);
    }
}
