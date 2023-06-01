package ru.maxima.school.projectmaximaedo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.maxima.school.projectmaximaedo.enums.Role;

import java.time.LocalDateTime;

@Entity
@Table(name="\"user\"")
public class User {
    /**
     * id юзера
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * логин
     */
    @Column(name = "login", nullable = false)
    private String login;
    /**
     * пароль
     */
    @Column(name = "password", nullable = false)
    private String password;
    /**
     * Время создание
     */
    //@CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    /**
     * Время обновления
     */
    //@UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    /**
     * Роль
     */
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    /**
     * Имя
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;
    /**
     * Фамилия
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;
    /**
     * Отчество
     */
    @Column(name = "patronymic", nullable = false)
    private String patronymic;
    /**
     * Телеграм
     */
    @Column(name = "telegram")
    private String telegram;
    /**
     * Почта
     */
    @Column(name = "email", nullable = false)
    private String email;
    /**
     * Последний визит
     */
    @Column(name = "last_visit", nullable = false)
    private LocalDateTime lastVisit;
    /**
     * Ссылка на сущность Document
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private Document document;
    /**
     * Флаг удаления
     */
    @Column(name = "is_removed", nullable = false)
    private Boolean isRemoved;

    public User() {
    }

    public User(String login, String password, LocalDateTime createdAt, LocalDateTime updatedAt, Role role,
                String firstName, String lastName, String patronymic, String telegram, String email, LocalDateTime lastVisit, Document document, Boolean isRemoved) {
        this.login = login;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.telegram = telegram;
        this.email = email;
        this.lastVisit = lastVisit;
        this.document = document;
        this.isRemoved = isRemoved;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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

    public LocalDateTime getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDateTime lastVisit) {
        this.lastVisit = lastVisit;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Boolean getRemoved() {
        return isRemoved;
    }

    public void setRemoved(Boolean removed) {
        isRemoved = removed;
    }
}
