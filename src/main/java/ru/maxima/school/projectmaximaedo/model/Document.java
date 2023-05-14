package ru.maxima.school.projectmaximaedo.model;

import jakarta.persistence.*;

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
    private String registryNumber;
    /**
     *ссылка на шаблон документа
     */
    @OneToOne(mappedBy = "document", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private DocumentTemplate template;
    /**
     * ссылка на контрагента
     */
    @OneToOne(mappedBy = "document", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Partner partner;
    /**
     * ссылка на файлы
     */
    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY, mappedBy = "document")
    private List<File> files;
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

    public Document() {
    }

    public Document(String name, LocalDateTime createdAt, String registryNumber, DocumentTemplate template,
                    Partner partner, List<File> files, User user, List<DocumentField> completedFields, Boolean isRemoved) {
        this.name = name;
        this.createdAt = createdAt;
        this.registryNumber = registryNumber;
        this.template = template;
        this.partner = partner;
        this.files = files;
        this.user = user;
        this.completedFields = completedFields;
        this.isRemoved = isRemoved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getRegistryNumber() {
        return registryNumber;
    }

    public void setRegistryNumber(String registryNumber) {
        this.registryNumber = registryNumber;
    }

    public DocumentTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DocumentTemplate template) {
        this.template = template;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
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

    /**
     * добавление файла в список
     */
    public void addFile(File file) {
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
    public void removeFile(File file){
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

