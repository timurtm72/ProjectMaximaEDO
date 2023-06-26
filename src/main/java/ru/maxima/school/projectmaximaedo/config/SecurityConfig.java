package ru.maxima.school.projectmaximaedo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import ru.maxima.school.projectmaximaedo.security.JWTFilter;
import ru.maxima.school.projectmaximaedo.security.SimpleAccessDeniedHandler;
import ru.maxima.school.projectmaximaedo.security.SimpleAuthenticationEntryPoint;
import ru.maxima.school.projectmaximaedo.security.CustomUserDetailsService;

@Configuration
public class SecurityConfig {
    private final CustomUserDetailsService personDetailsService;
    private final JWTFilter filter;

    @Autowired
    public SecurityConfig(CustomUserDetailsService personDetailsService, JWTFilter filter) {
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
                        authorize.requestMatchers("/users/register", "/users/login").permitAll()
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
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8081")
                .allowedMethods("*");
    }

}
