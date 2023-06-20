package ru.maxima.school.projectmaximaedo.service;

import ru.maxima.school.projectmaximaedo.dto.PartnerDto;

import java.util.List;

public interface PartnerService {
    /** Возвращает список сущностей PartnerDto */
    List<PartnerDto> getAll();
    /** Возвращает true, если объект с заданным id найден */
    Boolean exists(Long id);
    /** Возвращает PartnerDto объект с заданным id */
    PartnerDto getById(Long id);
    /** Создает сущность PartnerDto => Partner */
    Boolean create(PartnerDto dto);
    /** Обновляет сущность PartnerDto => Partner */
    Boolean update(PartnerDto dto,Long id);
    /** Безопасно удаляет объект с заданным id */
    Boolean safeDelete(Long id);

}
