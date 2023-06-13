package ru.maxima.school.projectmaximaedo.dto;

import jakarta.validation.constraints.NotBlank;

public class AttachedFileDto {
    /** ID */
    private Long id;
    /** Название файла */
    @NotBlank(message = "Имя файла не может быть пустым")
    private String name;
    /** Наименование хранилища */
    @NotBlank(message = "Путь к файлу не может быть пустым")
    private String nameOfStorage;
    /** Описание */
    @NotBlank(message = "Описание файла не может быть пустым")
    private String description;

    public AttachedFileDto() {
    }

    public AttachedFileDto(Long id, String name, String nameOfStorage, String description) {
        this.id = id;
        this.name = name;
        this.nameOfStorage = nameOfStorage;
        this.description = description;
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

    public String getNameOfStorage() {
        return nameOfStorage;
    }

    public void setNameOfStorage(String nameOfStorage) {
        this.nameOfStorage = nameOfStorage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
