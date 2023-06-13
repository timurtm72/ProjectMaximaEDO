package ru.maxima.school.projectmaximaedo.service;
import ru.maxima.school.projectmaximaedo.dto.DocumentDto;
import ru.maxima.school.projectmaximaedo.dto.DocumentFieldDto;
import java.util.List;

public interface DocumentService {
    /**
     *  Возвращает список сущностей DocumentDto
     */
    List<DocumentDto> getAll();
    /**
     *  Возвращает true, если есть объект с заданным id найден
     */
    Boolean exists(Long id);
    /**
     *  Возвращает DocumentDto объект с заданным id
     */
    DocumentDto getById(Long id);
    /**
     *  Создает сущность DocumentDto => Document
     */
    Boolean create(DocumentDto documentDto);
    /**
     *  Обновляет сущность DocumentDto => Document
     */
    Boolean update(DocumentDto documentDto,Long id);
    /**
     *  Безопасное удаляет объект с заданным id
     */
    Boolean safeDelete(Long id);
}
