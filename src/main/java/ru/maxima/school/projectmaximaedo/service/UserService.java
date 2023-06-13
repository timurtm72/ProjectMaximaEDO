package ru.maxima.school.projectmaximaedo.service;

import ru.maxima.school.projectmaximaedo.dto.UserDto;
import ru.maxima.school.projectmaximaedo.dto.UserReadDto;
import java.util.List;

public interface UserService {
    /**
     *  Возвращает список сущностей UserReadDto
     */
    List<UserReadDto> getAll();
    /**
     *  Возвращает true, если есть объект с заданным id найден
     */
    Boolean exists(Long id);

    /**
     * Возвращает true, если есть объект с заданным логином найден
     */
    public Boolean existsByLogin(String login);
    /**
     *  Возвращает UserReadDto объект с заданным id
     */
    UserReadDto getById(Long id);
    /**
     *  Создает сущность UserDto => User
     */
    Boolean create(UserDto userDto);

    /**
     *  Обновляет сущность UserDto => User
     */
    Boolean update(UserDto userDto, Long id);
    /**
     *  Безопасное удаляет объект с заданным id
     */
    Boolean safeDelete(Long id);
}
