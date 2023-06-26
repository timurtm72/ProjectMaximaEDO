package ru.maxima.school.projectmaximaedo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.maxima.school.projectmaximaedo.model.User;
import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user;
    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString()));
    }
    @Override
    public String getPassword() {
        return this.user.getPassword();
    }
    @Override
    public String getUsername() {
        return this.user.getLogin();
    }

    @Override//Срок действия учетной записи не истек
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override//Учетная запись не заблокирована
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override//Срок действия учетных данных не истек
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    // Нужно, чтобы получать данные аутентифицированного пользователя
    public User getUser(){
        return this.user;
    }
}
