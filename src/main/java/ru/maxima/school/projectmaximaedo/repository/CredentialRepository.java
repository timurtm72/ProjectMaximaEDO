package ru.maxima.school.projectmaximaedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxima.school.projectmaximaedo.model.Credential;

import java.util.List;
import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential,Long> {
    List<Credential> findAllByIsRemovedIsFalseOrderByIdAsc();
    Optional<Credential> findCredentialByIdAndIsRemovedIsFalse(Long id);
    Boolean existsByIdAndIsRemovedIsFalse(Long id);
}
