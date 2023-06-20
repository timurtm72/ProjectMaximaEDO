package ru.maxima.school.projectmaximaedo.model;

import jakarta.persistence.*;
import ru.maxima.school.projectmaximaedo.enums.CredentialType;
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
    @Transient
    private Long documentTemplateId;
    /**
     *ссылка на шаблон документа
     */
    @OneToOne(mappedBy = "document", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private DocumentTemplate template;
    /**
     * ID контрагента
     */
    @Transient
    private Long partnerId;
    /**
     * ссылка на контрагента
     */
    @OneToOne(mappedBy = "document", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Partner partner;
    /**
     * Список номеров файлов
     */
    @Transient
    private List<Integer> filesNumbers;
    /**
     * ссылка на файлы
     */
    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY, mappedBy = "document")
    private List<AttachedFile> files;
    /**
     * ID пользователя
     */
    @Transient
    private Long userId;
    /**
     * ссылка на пользователя
     */
    @OneToOne(mappedBy = "document", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private User user;
    /**
     * ссылка на поля документа
     */
    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)//, mappedBy = "document")
    //@JoinColumn(name = "document_id")
    private List<DocumentField> completedFields;
    /**
     * флаг удаления
     */
    @Column(name = "is_removed", nullable = false)
    private Boolean isRemoved;

    @Column(name = "document_type")
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    public Document() {
    }

    public Document(String name, LocalDateTime createdAt, Long registryNumber, Long documentTemplateId,
                    DocumentTemplate template, Long partnerId, Partner partner, List<Integer> filesNumbers, List<AttachedFile> files, Long userId, User user, List<DocumentField> completedFields, Boolean isRemoved, DocumentType documentType) {
        this.name = name;
        this.createdAt = createdAt;
        this.registryNumber = registryNumber;
        this.documentTemplateId = documentTemplateId;
        this.template = template;
        this.partnerId = partnerId;
        this.partner = partner;
        this.filesNumbers = filesNumbers;
        this.files = files;
        this.userId = userId;
        this.user = user;
        this.completedFields = completedFields;
        this.isRemoved = isRemoved;
        this.documentType = documentType;
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

    public Long getDocumentTemplateId() {
        return documentTemplateId;
    }

    public void setDocumentTemplateId(Long documentTemplateId) {
        this.documentTemplateId = documentTemplateId;
    }

    public DocumentTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DocumentTemplate template) {
        this.template = template;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    /**
     * добавление файла в список
     */
    public void addFile(AttachedFile file) {
        if(file != null) {
            files.add(file);
            file.setDocument(this);
        }else{
            throw(new  NullPointerException());
        }
    }

    /**
     * удаление файла из списка
     */
    public void removeFile(AttachedFile file){
        if(file != null) {
            files.remove(file);
            file.setDocument(null);
        }else{
            throw(new  NullPointerException());
        }
    }

    /**
     * добавление полей в список
     */
//    public void addField(DocumentTemplateField field) {
//        if(field != null) {
//            completedFields.add(field);
//            field.setDocument(this);
//        }else{
//            throw(new  NullPointerException());
//        }
//    }

    /**
     * удаление полей из списка
     */
//    public void removeField(DocumentTemplateField field) {
//        if (field != null) {
//            completedFields.remove(field);
//            field.setDocument(null);
//        }else{
//            throw(new  NullPointerException());
//        }
//    }
}

