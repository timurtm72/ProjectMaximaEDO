package ru.maxima.school.projectmaximaedo.service;

import ru.maxima.school.projectmaximaedo.dto.CommentDto;

import java.util.List;

public interface CommentService {
    /**
     *  Возвращает список сущностей CommentDto
     */
    List<CommentDto> getAll();
    /**
     *  Возвращает true, если есть объект с заданным id найден
     */
    Boolean exists(Long id);
    /**
     *  Возвращает DocumentFieldDto объект с заданным id
     */
    CommentDto getById(Long id);
    /**
     *  Создает сущность CommentDto => Comment
     */
    Boolean create(CommentDto commentDto);
    /**
     *  Обновляет сущность CommentDto => Comment
     */
    Boolean update(CommentDto commentDto,Long id);
    /**
     *  Безопасное удаляет объект с заданным id
     */
    Boolean safeDelete(Long id);

}
