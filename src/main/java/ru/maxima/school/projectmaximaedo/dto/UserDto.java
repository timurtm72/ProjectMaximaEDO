package ru.maxima.school.projectmaximaedo.dto;

import jakarta.validation.constraints.*;
import ru.maxima.school.projectmaximaedo.enums.Role;

public class UserDto {
    /**
     * id юзера
     */
    private Long id;
    /**
     * логин
     */
    @NotBlank(message = "Логин не может быть пустым")
    private String login;
    /**
     * пароль
     */
    @NotBlank(message = "Пароль не может быть пустым")
    private String password;
    /**
     * Роль
     */
    @NotNull(message = "Роль пользователя не может быть пустой")
    private Role role;
    /**
     * Имя
     */
    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;
    /**
     * Фамилия
     */
    @NotBlank(message = "Фамилия не может быть пустой")
    private String lastName;
    /**
     * Отчество
     */
    @NotBlank(message = "Отчество не может быть пустым")
    private String patronymic;
    /**
     * Телеграм
     */
    @Pattern(regexp = ".*\\B@(?=\\w{5,32}\\b)[a-zA-Z0-9]+(?:_[a-zA-Z0-9]+)*.*")
    private String telegram;
    /**
     * Почта
     */
    @Email
    private String email;

    public UserDto() {
    }

    public UserDto(Long id, String login, String password, Role role, String firstName, String lastName,
                   String patronymic, String telegram, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.telegram = telegram;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
