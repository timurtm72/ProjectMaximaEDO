package ru.maxima.school.projectmaximaedo.model;

import jakarta.persistence.*;
import ru.maxima.school.projectmaximaedo.enums.FieldType;

import java.util.List;

@Entity
@Table(name = "document_field")
public class DocumentField {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Наименование поля
     */
    @Column(name = "name", nullable = false)
    private String name;
    /**
     * Тип поля
     */
    @Column(name = "field_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private FieldType fieldType;
    /**
     * Плейсхолдер
     */
    @Column(name = "placeholder", nullable = false)
    private String placeholder;
    /**
     * Значение по умолчанию
     */
    @Column(name = "default_value")
    private String defaultValue;

    /** Флаг удаления */
    @Column(name = "is_removed", nullable = false)
    private boolean isRemoved;
    public DocumentField() {
    }

    public DocumentField(String name, FieldType fieldType, String placeholder, String defaultValue, boolean isRemoved) {
        this.name = name;
        this.fieldType = fieldType;
        this.placeholder = placeholder;
        this.defaultValue = defaultValue;
        this.isRemoved = isRemoved;
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

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }
}
