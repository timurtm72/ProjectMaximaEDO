package ru.maxima.school.projectmaximaedo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "partner")
public class Partner {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Фамилия
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;
    /**
     * имя
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;
    /**
     * Отчество
     */
    @Column(name = "patronymic", nullable = false)
    private String patronymic;
    /**
     * Телефон
     */
    @Column(name = "phone", nullable = false)
    private String phone;
    /**
     * телеграм
     */
    @Column(name = "telegram")
    private String telegram;
    /**
     * почта Email
     */
    @Column(name = "email", nullable = false)
    private String email;
    /**
     * Страна
     */
    @Column(name = "country", nullable = false)
    private String country;
    /**
     * Ссылка на сущность Комментарий
     */
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "partner")
    private List<Comment> comments;

    /**
     * Ссылка на сущность Document
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private Document document;
    /**
     * сылка на сущность Credential (полномочия)
     */
    @OneToOne(mappedBy = "partner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Credential credential;
    /**
     * флаг удаления
     */
    @Column(name = "is_removed", nullable = false)
    private boolean isRemoved;
    @Transient
    private List<Integer> commentsId;
    @Transient
    private Long credentialId;

    public Partner() {
    }

    public Partner(String lastName, String firstName, String patronymic, String phone, String telegram, String email,
                   String country, List<Comment> comments, Document document, Credential credential, boolean isRemoved, List<Integer> commentsId, Long credentialId) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.phone = phone;
        this.telegram = telegram;
        this.email = email;
        this.country = country;
        this.comments = comments;
        this.document = document;
        this.credential = credential;
        this.isRemoved = isRemoved;
        this.commentsId = commentsId;
        this.credentialId = credentialId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }

    public List<Integer> getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(List<Integer> commentsId) {
        this.commentsId = commentsId;
    }

    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Long credentialId) {
        this.credentialId = credentialId;
    }
}
