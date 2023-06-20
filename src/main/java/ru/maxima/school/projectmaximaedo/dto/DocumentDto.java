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
    @NotBlank(message = "ID шаблона документа не может быть пустым")
    private Long documentTemplateId;
    /**
     * ссылка на шаблон документа
     */
    private DocumentTemplateDto documentTemplateDto;
    /**
     * ID контрагента
     */
    @NotBlank(message = "ID контрагента не может быть пустым")
    private Long partnerId;
    /**
     * ссылка на контрагента
     */
    private PartnerDto partnerDto;
    /**
     * Список номеров файлов
     */
    @NotBlank(message = "Список номеров файлов не может быть пустым")
    private List<Integer> filesNumbers;
    /**
     * ссылка на файлы
     */
    private List<AttachedFileDto> filesDto;
    /**
     * ID пользователя
     */
    @NotBlank(message = "Номер пользователя не может быть пустым")
    private Long userId;
    /**
     * ссылка на пользователя
     */
    private UserDto userDto;
    /**
     * ссылка на поля документа
     */
    @NotBlank(message = "Список заполненных полей шабллона не может быть пустым")
    private List<DocumentFieldDto> completedFieldsDto;
    @NotBlank(message = "Тип документа не может быть пустым")
    private DocumentType documentType;

    public DocumentDto() {
    }

    public DocumentDto(Long id, String name, Long documentTemplateId, DocumentTemplateDto documentTemplateDto,
                       Long partnerId, PartnerDto partnerDto, List<Integer> filesNumbers, List<AttachedFileDto> filesDto, Long userId, UserDto userDto, List<DocumentFieldDto> completedFieldsDto, DocumentType documentType) {
        this.id = id;
        this.name = name;
        this.documentTemplateId = documentTemplateId;
        this.documentTemplateDto = documentTemplateDto;
        this.partnerId = partnerId;
        this.partnerDto = partnerDto;
        this.filesNumbers = filesNumbers;
        this.filesDto = filesDto;
        this.userId = userId;
        this.userDto = userDto;
        this.completedFieldsDto = completedFieldsDto;
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

    public Long getDocumentTemplateId() {
        return documentTemplateId;
    }

    public void setDocumentTemplateId(Long documentTemplateId) {
        this.documentTemplateId = documentTemplateId;
    }

    public DocumentTemplateDto getDocumentTemplateDto() {
        return documentTemplateDto;
    }

    public void setDocumentTemplateDto(DocumentTemplateDto documentTemplateDto) {
        this.documentTemplateDto = documentTemplateDto;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public PartnerDto getPartnerDto() {
        return partnerDto;
    }

    public void setPartnerDto(PartnerDto partnerDto) {
        this.partnerDto = partnerDto;
    }

    public List<Integer> getFilesNumbers() {
        return filesNumbers;
    }

    public void setFilesNumbers(List<Integer> filesNumbers) {
        this.filesNumbers = filesNumbers;
    }

    public List<AttachedFileDto> getFilesDto() {
        return filesDto;
    }

    public void setFilesDto(List<AttachedFileDto> filesDto) {
        this.filesDto = filesDto;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<DocumentFieldDto> getCompletedFieldsDto() {
        return completedFieldsDto;
    }

    public void setCompletedFieldsDto(List<DocumentFieldDto> completedFieldsDto) {
        this.completedFieldsDto = completedFieldsDto;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }
}
