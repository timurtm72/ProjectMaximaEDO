package ru.maxima.school.projectmaximaedo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.maxima.school.projectmaximaedo.enums.DocumentType;

import java.util.List;

public class DocumentDto {
    /**
     * ID
     */
    private Long id;
    /**
     * название документа
     */
    @NotBlank(message = "Название документа не может быть пустым")
    private String name;
    /**
     * ID шаблона документа
     */
    @NotNull(message = "ID шаблона документа не может быть пустым")
    private Long docTemplateId;
    /**
     * ссылка на шаблон документа
     */
    private DocumentTemplateDto template;
    /**
     * ID контрагента
     */
    @NotNull(message = "ID контрагента не может быть пустым")
    private Long partId;
    /**
     * ссылка на контрагента
     */
    private PartnerDto partner;
    /**
     * Список номеров файлов
     */
    @NotNull(message = "Список номеров файлов не может быть пустым")
    private List<Integer> filesNumbers;
    /**
     * ссылка на файлы
     */
    private List<AttachedFileDto> files;
    /**
     * ID пользователя
     */
    @NotNull(message = "Номер пользователя не может быть пустым")
    private Long usrId;
    /**
     * ссылка на пользователя
     */
    private UserReadDto user;
    /**
     * ссылка на поля документа
     */
    @NotNull(message = "Список заполненных полей шабллона не может быть пустым")
    private List<DocumentFieldDto> completedFields;
    @NotNull(message = "Тип документа не может быть пустым")
    private DocumentType documentType;

    public DocumentDto() {
    }

    public DocumentDto(Long id, String name, Long docTemplateId, DocumentTemplateDto template, Long partId,
                       PartnerDto partner, List<Integer> filesNumbers, List<AttachedFileDto> files, Long usrId, UserReadDto user, List<DocumentFieldDto> completedFields, DocumentType documentType) {
        this.id = id;
        this.name = name;
        this.docTemplateId = docTemplateId;
        this.template = template;
        this.partId = partId;
        this.partner = partner;
        this.filesNumbers = filesNumbers;
        this.files = files;
        this.usrId = usrId;
        this.user = user;
        this.completedFields = completedFields;
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

    public Long getDocTemplateId() {
        return docTemplateId;
    }

    public void setDocTemplateId(Long docTemplateId) {
        this.docTemplateId = docTemplateId;
    }

    public DocumentTemplateDto getTemplate() {
        return template;
    }

    public void setTemplate(DocumentTemplateDto template) {
        this.template = template;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public PartnerDto getPartner() {
        return partner;
    }

    public void setPartner(PartnerDto partner) {
        this.partner = partner;
    }

    public List<Integer> getFilesNumbers() {
        return filesNumbers;
    }

    public void setFilesNumbers(List<Integer> filesNumbers) {
        this.filesNumbers = filesNumbers;
    }

    public List<AttachedFileDto> getFiles() {
        return files;
    }

    public void setFiles(List<AttachedFileDto> files) {
        this.files = files;
    }

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    public UserReadDto getUser() {
        return user;
    }

    public void setUser(UserReadDto user) {
        this.user = user;
    }

    public List<DocumentFieldDto> getCompletedFields() {
        return completedFields;
    }

    public void setCompletedFields(List<DocumentFieldDto> completedFields) {
        this.completedFields = completedFields;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }
}
