package ru.maxima.school.projectmaximaedo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.maxima.school.projectmaximaedo.dto.UserDto;
import ru.maxima.school.projectmaximaedo.model.User;
import ru.maxima.school.projectmaximaedo.utils.MapperUtil;

@Component
public class UserMapper implements IMapper<User, UserDto>{
    private final MapperUtil mapperUtil;
    @Autowired
    public UserMapper(MapperUtil mapperUtil) {
        this.mapperUtil = mapperUtil;
    }

    @Override
    public UserDto toDto(User user) {
        return mapperUtil.getMapper().map(user,UserDto.class);
    }

    @Override
    public User toEntity(UserDto userDto) {
        return mapperUtil.getMapper().map(userDto,User.class);
    }
}
