package ru.maxima.school.projectmaximaedo.dto;

import jakarta.validation.constraints.NotNull;

public class LoginDto {
    @NotNull(message = "Логин не может быть пустым")
    private String login;
    @NotNull(message = "Пароль не может быть пустым")
    private String password;

    public LoginDto() {
    }

    public LoginDto(String login, String password) {
        this.login = login;
        this.password = password;
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
}
