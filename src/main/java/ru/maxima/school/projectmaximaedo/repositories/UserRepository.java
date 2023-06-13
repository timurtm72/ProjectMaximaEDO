package ru.maxima.school.projectmaximaedo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxima.school.projectmaximaedo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByIsRemovedIsFalseOrderByIdAsc();
    Optional<User> findUserById(Long id);
    Boolean existsUserByLogin(String login);

    Optional<User> findByName(String name);
}
