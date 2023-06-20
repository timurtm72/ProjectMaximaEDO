package ru.maxima.school.projectmaximaedo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

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
    private List<CommentDto> commentsDto;
    @NotBlank(message = "Список номеров комеентариев не может быть пустым")
    private List<Integer> commentsId;
    private CredentialDto credentialDto;
    @NotBlank(message = "Номер учетных данных не может быть пустым")
    private Long credentialId;

    public PartnerDto() {
    }

    public PartnerDto(Long id, String lastName, String firstName, String patronymic, String phone, String telegram,
                      String email, String country, List<CommentDto> commentsDto, List<Integer> commentsId, CredentialDto credentialDto, Long credentialId) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.phone = phone;
        this.telegram = telegram;
        this.email = email;
        this.country = country;
        this.commentsDto = commentsDto;
        this.commentsId = commentsId;
        this.credentialDto = credentialDto;
        this.credentialId = credentialId;
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

    public List<CommentDto> getCommentsDto() {
        return commentsDto;
    }

    public void setCommentsDto(List<CommentDto> commentsDto) {
        this.commentsDto = commentsDto;
    }

    public List<Integer> getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(List<Integer> commentsId) {
        this.commentsId = commentsId;
    }

    public CredentialDto getCredentialDto() {
        return credentialDto;
    }

    public void setCredentialDto(CredentialDto credentialDto) {
        this.credentialDto = credentialDto;
    }

    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Long credentialId) {
        this.credentialId = credentialId;
    }
}
