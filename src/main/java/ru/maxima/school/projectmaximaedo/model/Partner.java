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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "partner_id")
    private List<Comment> comments;

    /**
     * Ссылка на сущность Document
     */
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "document_id")
//    private Document document;
    /**
     * сылка на сущность Credential (полномочия)
     */
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Credential credential;
    //@Transient
    private List<Integer> commId;
    //@Transient
    private Long credId;
    /**
     * флаг удаления
     */
    @Column(name = "is_removed", nullable = false)
    private boolean isRemoved;

    public Partner() {
    }

    public Partner(Long id, String lastName, String firstName, String patronymic, String phone, String telegram,
                   String email, String country, List<Comment> comments, Credential credential, List<Integer> commId, Long credId, boolean isRemoved) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.phone = phone;
        this.telegram = telegram;
        this.email = email;
        this.country = country;
        this.comments = comments;
        this.credential = credential;
        this.commId = commId;
        this.credId = credId;
        this.isRemoved = isRemoved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public List<Integer> getCommId() {
        return commId;
    }

    public void setCommId(List<Integer> commId) {
        this.commId = commId;
    }

    public Long getCredId() {
        return credId;
    }

    public void setCredId(Long credId) {
        this.credId = credId;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }
}
