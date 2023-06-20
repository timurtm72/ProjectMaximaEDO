package ru.maxima.school.projectmaximaedo.service;

import ru.maxima.school.projectmaximaedo.dto.DocumentTemplateDto;
import ru.maxima.school.projectmaximaedo.model.DocumentTemplate;

import java.util.List;

public interface DocumentTemplateService {
    /**
     *  Возвращает список сущностей DocumentTemplateDto
     */
    List<DocumentTemplateDto> getAll();
    /**
     *  Возвращает true, если есть объект с заданным id найден
     */
    Boolean exists(Long id);
    /**
     *  Возвращает DocumentTemplateDto объект с заданным id
     */
    DocumentTemplateDto getById(Long id);
    /**
     *  Создает сущность DocumentTemplateDto => DocumentTemplate
     */
    Boolean create(DocumentTemplateDto documentTemplateDto);
    /**
     *  Обновляет сущность DocumentTemplateDto => DocumentTemplate
     */
    Boolean update(DocumentTemplateDto documentTemplateDto,Long id);
    /**
     *  Безопасное удаляет объект с заданным id
     */
    Boolean safeDelete(Long id);

}
