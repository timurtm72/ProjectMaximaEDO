package ru.maxima.school.projectmaximaedo.model;

import jakarta.persistence.*;
import ru.maxima.school.projectmaximaedo.enums.DocumentType;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "document")
public class Document {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * название документа
     */
    @Column(name = "name", nullable = false)
    private String name;
    /**
     * дата создания
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    /**
     * регистрационный номер документа
     */
    @Column(name = "registry_number", nullable = false)
    private Long registryNumber;
    /**
     * ID шаблона документа
     */
    //@Transient
    private Long docTemplateId;
    /**
     *ссылка на шаблон документа
     */
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "template_id")
    private DocumentTemplate template;
    /**
     * ID контрагента
     */
    //@Transient
    private Long partId;
    /**
     * ссылка на контрагента
     */
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "partner_id")
    private Partner partner;
    /**
     * Список номеров файлов
     */
    //@Transient
    private List<Integer> filesNumbers;
    /**
     * ссылка на файлы
     */
    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "files_id")
    private List<AttachedFile> files;
    /**
     * ID пользователя
     */
    //@Transient
    private Long usrId;
    /**
     * ссылка на пользователя
     */
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    /**
     * ссылка на поля документа
     */
    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "completed_files_id")
    private List<DocumentField> completedFields;
    /**
     * флаг удаления
     */
    @Column(name = "is_removed", nullable = false)
    private Boolean isRemoved;

    @Column(name = "doc_type")
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    public Document() {
    }

    public Document(Long id, String name, LocalDateTime createdAt, Long registryNumber, Long docTemplateId,
                    DocumentTemplate template, Long partId, Partner partner, List<Integer> filesNumbers, List<AttachedFile> files, Long usrId, User user, List<DocumentField> completedFields, Boolean isRemoved, DocumentType documentType) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.registryNumber = registryNumber;
        this.docTemplateId = docTemplateId;
        this.template = template;
        this.partId = partId;
        this.partner = partner;
        this.filesNumbers = filesNumbers;
        this.files = files;
        this.usrId = usrId;
        this.user = user;
        this.completedFields = completedFields;
        this.isRemoved = isRemoved;
        this.documentType = documentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getRegistryNumber() {
        return registryNumber;
    }

    public void setRegistryNumber(Long registryNumber) {
        this.registryNumber = registryNumber;
    }

    public Long getDocTemplateId() {
        return docTemplateId;
    }

    public void setDocTemplateId(Long docTemplateId) {
        this.docTemplateId = docTemplateId;
    }

    public DocumentTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DocumentTemplate template) {
        this.template = template;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public List<Integer> getFilesNumbers() {
        return filesNumbers;
    }

    public void setFilesNumbers(List<Integer> filesNumbers) {
        this.filesNumbers = filesNumbers;
    }

    public List<AttachedFile> getFiles() {
        return files;
    }

    public void setFiles(List<AttachedFile> files) {
        this.files = files;
    }

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<DocumentField> getCompletedFields() {
        return completedFields;
    }

    public void setCompletedFields(List<DocumentField> completedFields) {
        this.completedFields = completedFields;
    }

    public Boolean getRemoved() {
        return isRemoved;
    }

    public void setRemoved(Boolean removed) {
        isRemoved = removed;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType docType) {
        this.documentType = docType;
    }
}

