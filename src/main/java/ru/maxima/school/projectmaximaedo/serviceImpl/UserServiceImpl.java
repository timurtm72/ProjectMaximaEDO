package ru.maxima.school.projectmaximaedo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.school.projectmaximaedo.dto.UserDto;
import ru.maxima.school.projectmaximaedo.dto.UserReadDto;
import ru.maxima.school.projectmaximaedo.mapper.UserMapper;
import ru.maxima.school.projectmaximaedo.mapper.UserReadMapper;
import ru.maxima.school.projectmaximaedo.model.User;
import ru.maxima.school.projectmaximaedo.repositories.UserRepository;

import ru.maxima.school.projectmaximaedo.service.UserService;
import ru.maxima.school.projectmaximaedo.utils.MapperUtil;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserReadMapper userReadMapper;
    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;
    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserReadMapper userReadMapper, UserRepository userRepository, MapperUtil mapperUtil) {
        this.userMapper = userMapper;
        this.userReadMapper = userReadMapper;
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    @Transactional
    public List<UserReadDto> getAll() {
        List<User> users = userRepository.findAllByIsRemovedIsFalseOrderByIdAsc();
        if(users == null || users.size() == 0 ){
            return null;
        }
        return mapperUtil.mapList(users, UserReadDto.class);
    }

    @Override
    @Transactional
    public Boolean exists(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    @Transactional
    public Boolean existsByLogin(String login) {
        return userRepository.existsUserByLogin(login);
    }

    @Override
    @Transactional
    public UserReadDto getById(Long id) {
        User user = userRepository.findUserById(id).orElse(null);
        return user != null ? userReadMapper.toDto(user) : null;
    }

    @Override
    public Boolean create(UserDto userDto) {
        if(userDto == null){
            return true;
        }
       User user = userMapper.toEntity(userDto);
        if(user != null){
            user.setCreatedAt(LocalDateTime.now());
            user.setLastVisit(LocalDateTime.now());
            user.setRemoved(false);
            userRepository.save(user);
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean update(UserDto userDto, Long id) {
        if (userDto == null) {
            return true;
        }
        userDto.setId(id);
        User user = userMapper.toEntity(userDto);
        User readUser = userRepository.findUserById(id).orElse(null);
        if (readUser != null) {
            user.setCreatedAt(readUser.getCreatedAt());
            user.setRemoved(readUser.getRemoved());
            user.setUpdatedAt(LocalDateTime.now());
            user.setLastVisit(LocalDateTime.now());
            userRepository.save(user);
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean safeDelete(Long id) {
        User user = userRepository.findUserById(id).orElse(null);
        if(user != null){
            user.setRemoved(true);
            userRepository.save(user);
            return false;
        }
        return true;
    }
}
