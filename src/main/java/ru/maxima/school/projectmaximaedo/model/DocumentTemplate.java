package ru.maxima.school.projectmaximaedo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "document_template")
public class DocumentTemplate {
    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** Название */
    @Column(name = "name", nullable = false)
    private String name;
    /** Время создания */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    /** Версия */
    @Column(name = "version", nullable = false)
    private Integer version;
    /** Ссылка на документ*/
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private Document document;
    /** Ссылка на поля документа*/
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)//, mappedBy = "document_template")
    //@JoinColumn(name="document_template_id")
    private List<DocumentField> templateFields;

    private List<Integer> templateFieldNumbers;

    /** Флаг удаления */
    @Column(name = "is_removed", nullable = false)
    private boolean isRemoved;

    public DocumentTemplate() {
    }

    public DocumentTemplate(String name, LocalDateTime createdAt, Integer version, Document document,
                            List<DocumentField> templateFields, List<Integer> templateFieldNumbers, boolean isRemoved) {
        this.name = name;
        this.createdAt = createdAt;
        this.version = version;
        this.document = document;
        this.templateFields = templateFields;
        this.templateFieldNumbers = templateFieldNumbers;
        this.isRemoved = isRemoved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DocumentField> getTemplateFields() {
        return templateFields;
    }

    public void setTemplateFields(List<DocumentField> templateFields) {
        this.templateFields = templateFields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }

    public List<Integer> getTemplateFieldNumbers() {
        return templateFieldNumbers;
    }

    public void setTemplateFieldNumbers(List<Integer> templateFieldNumbers) {
        this.templateFieldNumbers = templateFieldNumbers;
    }
}
