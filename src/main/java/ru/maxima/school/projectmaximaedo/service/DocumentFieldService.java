package ru.maxima.school.projectmaximaedo.service;

import ru.maxima.school.projectmaximaedo.dto.DocumentFieldDto;
import java.util.List;

public interface DocumentFieldService {
    /**
     *  Возвращает список сущностей DocumentFieldDto
     */
    List<DocumentFieldDto> getAll();
    /**
     *  Возвращает true, если есть объект с заданным id найден
     */
    Boolean exists(Long id);
    /**
     *  Возвращает DocumentFieldDto объект с заданным id
     */
    DocumentFieldDto getById(Long id);
    /**
     *  Создает сущность DocumentFieldDto => DocumentField
     */
    Boolean create(DocumentFieldDto documentFieldDto);
    /**
     *  Обновляет сущность DocumentFieldDto => DocumentField
     */
    Boolean update(DocumentFieldDto documentFieldDto,Long id);
    /**
     *  Безопасное удаляет объект с заданным id
     */
    Boolean safeDelete(Long id);

}
