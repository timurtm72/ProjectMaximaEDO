package ru.maxima.school.projectmaximaedo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.maxima.school.projectmaximaedo.dto.UserReadDto;
import ru.maxima.school.projectmaximaedo.model.User;
import ru.maxima.school.projectmaximaedo.utils.MapperUtil;
@Component
public class UserReadMapper implements IMapper<User, UserReadDto>{
    private final MapperUtil mapperUtil;
    @Autowired
    public UserReadMapper(MapperUtil mapperUtil) {
        this.mapperUtil = mapperUtil;
    }

    @Override
    public UserReadDto toDto(User user) {
        return mapperUtil.getMapper().map(user,UserReadDto.class);
    }

    @Override
    public User toEntity(UserReadDto userReadDto) {
        return mapperUtil.getMapper().map(userReadDto,User.class);
    }
}
