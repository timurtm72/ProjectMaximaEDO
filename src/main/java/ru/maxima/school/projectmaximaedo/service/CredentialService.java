package ru.maxima.school.projectmaximaedo.service;

import ru.maxima.school.projectmaximaedo.dto.CredentialDto;

import java.util.List;

public interface CredentialService {
    /**
     *  Возвращает список сущностей CredentialDto
     */
    List<CredentialDto> getAll();
    /**
     *  Возвращает true, если есть объект с заданным id найден
     */
    Boolean exists(Long id);
    /**
     *  Возвращает CredentialDto объект с заданным id
     */
    CredentialDto getById(Long id);
    /**
     *  Создает сущность CredentialDto => Credential
     */
    Boolean create(CredentialDto credentialDto);
    /**
     *  Обновляет сущность CredentialDto => Credential
     */
    Boolean update(CredentialDto credentialDto,Long id);
    /**
     *  Безопасное удаляет объект с заданным id
     */
    Boolean safeDelete(Long id);
}
