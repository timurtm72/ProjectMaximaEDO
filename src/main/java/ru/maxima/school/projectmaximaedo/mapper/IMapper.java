package ru.maxima.school.projectmaximaedo.mapper;

public interface IMapper<Entity,Dto> {
    Dto toDto(Entity entity);
    Entity toEntity(Dto dto);
}
