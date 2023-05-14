package ru.maxima.school.projectmaximaedo.model;

import jakarta.persistence.*;
import ru.maxima.school.projectmaximaedo.enums.CredentialType;

import java.time.LocalDateTime;

@Entity
@Table(name = "credential")
public class Credential {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * текст
     */
    @Column(name = "text")
    private String text;
    /**
     * время создания
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    /**
     * версия
     */
    @Column(name = "version")
    private Integer version;
    /**
     *тип лица: физ лицо, юр лицо
     */
    @Column(name = "credential_type")
    @Enumerated(EnumType.STRING)
    private CredentialType credentialType;
    /**
     * ссылка на контрагента
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id")
    private Partner partner;
    /**
     * флаг удаления
     */
    @Column(name = "is_removed", nullable = false)
    private Boolean isRemoved;

    public Credential() {
    }

    public Credential(String text, LocalDateTime createdAt, Integer version,
                      CredentialType credentialType, Partner partner, Boolean isRemoved) {
        this.text = text;
        this.createdAt = createdAt;
        this.version = version;
        this.credentialType = credentialType;
        this.partner = partner;
        this.isRemoved = isRemoved;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public CredentialType getCredentialType() {
        return credentialType;
    }

    public void setCredentialType(CredentialType credentialType) {
        this.credentialType = credentialType;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public Boolean getRemoved() {
        return isRemoved;
    }

    public void setRemoved(Boolean removed) {
        isRemoved = removed;
    }
}
