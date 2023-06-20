package ru.maxima.school.projectmaximaedo.dto;

import jakarta.validation.constraints.NotBlank;

public class CommentDto {
    private Long id;
    @NotBlank(message="Комментарий не может быть пустым")
    private String text;

    public CommentDto() {
    }

    public CommentDto(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
