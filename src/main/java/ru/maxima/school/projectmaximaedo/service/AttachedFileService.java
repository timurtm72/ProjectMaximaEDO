package ru.maxima.school.projectmaximaedo.service;

import org.springframework.transaction.annotation.Transactional;
import ru.maxima.school.projectmaximaedo.dto.AttachedFileDto;
import ru.maxima.school.projectmaximaedo.model.AttachedFile;

import java.util.List;

public interface AttachedFileService {
    /**
     *  Возвращает список сущностей FileDto
     */
    List<AttachedFileDto> getAll();
    /**
     *  Возвращает true, если есть объект с заданным id найден
     */
    Boolean exists(Long id);
    /**
     *  Возвращает FileDto объект с заданным id
     */
    AttachedFileDto getById(Long id);
    /**
     *  Создает сущность FileDto => File
     */
    Boolean create(AttachedFile file);

    /**
     *  Обновляет сущность FileDto => File
     */
    Boolean update(AttachedFileDto fileDto, Long id);
    /**
     *  Безопасное удаляет объект с заданным id
     */
    Boolean safeDelete(Long id);

}
