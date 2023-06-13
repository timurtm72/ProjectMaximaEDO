package ru.maxima.school.projectmaximaedo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.maxima.school.projectmaximaedo.jwt_util.SimpleAccessDeniedHandler;
import ru.maxima.school.projectmaximaedo.jwt_util.SimpleAuthenticationEntryPoint;
import ru.maxima.school.projectmaximaedo.security.JWTFilter;
import ru.maxima.school.projectmaximaedo.service.PersonDetailsService;

public class SecurityConfig {
    private PersonDetailsService personDetailsService;
    private JWTFilter filter;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService, JWTFilter filter) {
        this.personDetailsService = personDetailsService;
        this.filter = filter;
    }
    //Настройки для REST
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic().disable()
                .cors()
                .and()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/api/register", "/api/login").permitAll()
                                .anyRequest().authenticated())
                .userDetailsService(personDetailsService)
                .exceptionHandling()
                .accessDeniedHandler(new SimpleAccessDeniedHandler())
                .authenticationEntryPoint(new SimpleAuthenticationEntryPoint())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    protected PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
