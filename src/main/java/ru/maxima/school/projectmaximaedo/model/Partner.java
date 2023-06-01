package ru.maxima.school.projectmaximaedo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "partner")
public class Partner {
     /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** Фамилия */
    @Column(name = "last_name", nullable = false)
    private String LastName;
    /** имя */
    @Column(name = "first_name", nullable = false)
    private String firstName;
    /** Отчество */
    @Column(name = "patronymic", nullable = false)
    private String patronymic;
    /** Телефон */
    @Column(name = "phone", nullable = false)
    private String phone;
    /** телеграм */
    @Column(name = "telegram")
    private String telegram;
    /** почта Email */
    @Column(name = "email", nullable = false)
    private String email;
    /** Страна */
    @Column(name = "country", nullable = false)
    private String country;
    /** Ссылка на сущность Комментарий */
    @OneToMany(cascade = CascadeType.MERGE , fetch = FetchType.LAZY, mappedBy = "partner")
    private List<Comment> comments;

    /** Ссылка на сущность Document */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private Document document;
    /** сылка на сущность Credential (полномочия) */
    @OneToOne(mappedBy = "partner", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Credential credential;
    /** флаг удаления */
    @Column(name = "is_removed", nullable = false)
    private boolean isRemoved;

    public Partner() {
    }

    public Partner(String patronymic, String firstName, String lastName, String phone, String telegram, String email,
                   String country, List<Comment> comments, Credential credential, boolean isRemoved) {
        this.patronymic = patronymic;
        this.firstName = firstName;
        this.LastName = lastName;
        this.phone = phone;
        this.telegram = telegram;
        this.email = email;
        this.country = country;
        this.comments = comments;
        this.credential = credential;
        this.isRemoved = isRemoved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
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

    public void setRemoved(boolean removed) {
        isRemoved = removed;
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

    public void setIsRemoved(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }
    /**
     * добавление комментарии в список
     */
    public void addComment(Comment comment) {
        if(comment != null) {
            comments.add(comment);
            comment.setPartner(this);
        }else{
            throw(new  NullPointerException());
        }
    }

    /**
     * удаление комментарий из списка
     */
    public void removeComment(Comment comment) {
        if (comment != null) {
            comments.remove(comment);
            comment.setPartner(null);
        }else{
            throw(new  NullPointerException());
        }
    }

}
