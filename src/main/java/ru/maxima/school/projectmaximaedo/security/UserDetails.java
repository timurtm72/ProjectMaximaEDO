package ru.maxima.school.projectmaximaedo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.maxima.school.projectmaximaedo.model.User;

import java.util.Collection;
import java.util.Collections;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private User user;

    @Autowired
    public UserDetails(User person) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole());
        return null;
    }
    @Override
    public String getPassword() {
        return this.user.getPassword();
    }
    @Override
    public String getUsername() {
        return this.user.getFirstName();
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
    public User getPerson(){
        return this.user;
    }
}
