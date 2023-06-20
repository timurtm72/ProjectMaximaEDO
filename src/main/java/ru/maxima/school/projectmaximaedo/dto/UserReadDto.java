package ru.maxima.school.projectmaximaedo.dto;

import ru.maxima.school.projectmaximaedo.enums.Role;

public class UserReadDto {
    /**
     * id юзера
     */
    private Long id;
    /**
     * логин
     */
    private String login;
    /**
     * Роль
     */
    private Role role;
    /**
     * Имя
     */
    private String firstName;
    /**
     * Фамилия
     */
    private String lastName;
    /**
     * Отчество
     */
    private String patronymic;
    /**
     * Телеграм
     */
    private String telegram;
    /**
     * Почта
     */
    private String email;

    public UserReadDto() {
    }

    public UserReadDto(Long id, String login, Role role, String firstName, String lastName, String patronymic,
                       String telegram, String email) {
        this.id = id;
        this.login = login;
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
