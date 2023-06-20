package ru.maxima.school.projectmaximaedo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Текст
     */
    @Column(name = "text", nullable = false)
    private String text;
    /**
     * Время создания
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    /**
     * Ссылка на контрагента
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="partner_id")
    private Partner partner;
    /**
     * Флаг удаления
     */
    @Column(name = "is_removed", nullable = false)
    private Boolean isRemoved;

    public Comment() {
    }

    public Comment(String text, LocalDateTime createdAt, Partner partner, Boolean isRemoved) {
        this.text = text;
        this.createdAt = createdAt;
        this.partner = partner;
        this.isRemoved = isRemoved;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
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

    public Boolean getRemoved() {
        return isRemoved;
    }

    public void setRemoved(Boolean removed) {
        isRemoved = removed;
    }

}
