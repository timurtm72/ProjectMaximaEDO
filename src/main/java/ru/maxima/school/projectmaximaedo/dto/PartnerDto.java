package ru.maxima.school.projectmaximaedo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import ru.maxima.school.projectmaximaedo.model.Document;

import java.util.List;

public class PartnerDto {
    /** ID */
    private Long id;
    @NotBlank(message = "Фамилия не может быть пустой")
    private String lastName;
    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;
    @NotBlank(message = "Отчество не может быть пустым")
    private String patronymic;
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")
    private String phone;
    @Pattern(regexp = ".*\\B@(?=\\w{5,32}\\b)[a-zA-Z0-9]+(?:_[a-zA-Z0-9]+)*.*")
    private String telegram;
    @Email
    private String email;
    @NotBlank(message = "Название страны не может быть пустым")
    private String country;
    private List<CommentDto> comments;
    //private Document document;
    private CredentialDto credential;
    @NotNull(message = "Список номеров комеентариев не может быть пустым")
    private List<Integer> commId;
    @NotNull(message = "Номер учетных данных не может быть пустым")
    private Long credId;

    public PartnerDto() {
    }

    public PartnerDto(Long id, String lastName, String firstName, String patronymic, String phone,
                      String telegram, String email, String country, List<CommentDto> comments, CredentialDto credential, List<Integer> commId, Long credId) {
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

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public CredentialDto getCredential() {
        return credential;
    }

    public void setCredential(CredentialDto credential) {
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
}
