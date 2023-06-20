package ru.maxima.school.projectmaximaedo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.maxima.school.projectmaximaedo.dto.AuthResponseDTO;
import ru.maxima.school.projectmaximaedo.dto.LoginDto;
import ru.maxima.school.projectmaximaedo.dto.UserDto;
import ru.maxima.school.projectmaximaedo.dto.UserReadDto;
import ru.maxima.school.projectmaximaedo.security.JWTUtil;
import ru.maxima.school.projectmaximaedo.service.UserService;
import ru.maxima.school.projectmaximaedo.utils.Response;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtGenerator;
    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTUtil jwtGenerator) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getLogin(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication.getName());
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@Valid @RequestBody UserDto userDto) {
        if (userService.existsByLogin(userDto.getLogin())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Такой логин уже занят");
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        if (userService.create(userDto)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ошибка в регистрации пользователя");
        }
        return ResponseEntity.accepted().body(new Response("Пользователь успешно зарегистрирован", LocalDateTime.now()));
    }

    @GetMapping()
    public ResponseEntity<List<UserReadDto>> getUsers(){
        List<UserReadDto> users = userService.getAll();
        if(users == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Список пользователей пуст");
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserReadDto> getUser(@PathVariable("id") Long id) {
        UserReadDto user = userService.getById(id);
        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Пользователь с  " + id + " не найден");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateUser(@PathVariable("id") Long id,
                                               @Valid @RequestBody UserDto userDto) {
        if (userService.update(userDto,id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Пользователь с  " + id + " не найден");
        }
        return ResponseEntity.accepted().body(new Response("Обновление пользователя прошло успешно", LocalDateTime.now()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable("id") Long id) {
        if (userService.safeDelete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Неверно заданный id для удаления");
        }
        return ResponseEntity.accepted().body(new Response("Удаление пользователя прошло успешно", LocalDateTime.now()));
    }
}