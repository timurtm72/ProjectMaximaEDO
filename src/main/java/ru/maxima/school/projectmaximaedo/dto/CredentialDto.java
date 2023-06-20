package ru.maxima.school.projectmaximaedo.dto;

import jakarta.validation.constraints.NotBlank;
import ru.maxima.school.projectmaximaedo.enums.CredentialType;

public class CredentialDto {
    /**
     * ID
     */
    private Long id;
    /**
     * текст
     */
    @NotBlank(message = "Комментарий не может быть пустым")
    private String text;
    /**
     *тип лица: физ лицо, юр лицо
     */
    @NotBlank(message = "Тип комментария не может быть пустым")
    private CredentialType credentialType;

    public CredentialDto() {
    }

    public CredentialDto(Long id, String text, CredentialType credentialType) {
        this.id = id;
        this.text = text;
        this.credentialType = credentialType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CredentialType getCredentialType() {
        return credentialType;
    }

    public void setCredentialType(CredentialType credentialType) {
        this.credentialType = credentialType;
    }
}
