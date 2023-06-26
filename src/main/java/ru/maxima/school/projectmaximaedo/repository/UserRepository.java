package ru.maxima.school.projectmaximaedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxima.school.projectmaximaedo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByIsRemovedIsFalseOrderByIdAsc();
    Optional<User> findUserByIdAndIsRemovedIsFalse(Long id);
    Boolean existsByIdAndIsRemovedIsFalse(Long id);
    Boolean existsUserByLoginAndIsRemovedIsFalse(String login);
    Optional<User> findUserByFirstNameAndIsRemovedIsFalse(String name);
    Optional<User> findUserByLoginAndIsRemovedIsFalse(String login);
}
