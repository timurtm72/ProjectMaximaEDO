package ru.maxima.school.projectmaximaedo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.maxima.school.projectmaximaedo.enums.FieldType;

public class DocumentFieldDto {
    /**
     * ID
     */
    private Long id;
    /**
     * Наименование поля
     */
    @NotBlank(message = "Название поля не может быть пустым")
    private String name;
    /**
     * Тип поля
     */
    @NotNull(message = "Тип поля должны быть не null")
    private FieldType fieldType;
    /**
     * Плейсхолдер
     */
    @NotBlank(message = "Заполнитель поля  шаблона не может быть пустым")
    private String placeholder;
    /**
     * Значение по умолчанию
     */
    @NotBlank(message = "Значение по умолчанию не может быть пустым")
    private String defaultValue;

    public DocumentFieldDto() {
    }

    public DocumentFieldDto(Long id, String name, FieldType fieldType, String placeholder, String defaultValue) {
        this.id = id;
        this.name = name;
        this.fieldType = fieldType;
        this.placeholder = placeholder;
        this.defaultValue = defaultValue;
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

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
