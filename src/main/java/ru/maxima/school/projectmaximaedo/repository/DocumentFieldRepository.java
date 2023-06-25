package ru.maxima.school.projectmaximaedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxima.school.projectmaximaedo.model.DocumentField;

import java.util.List;
import java.util.Optional;

public interface DocumentFieldRepository extends JpaRepository<DocumentField,Long> {
    List<DocumentField> findAllByIsRemovedIsFalseOrderByIdAsc();
    Optional<DocumentField> findDocumentFieldByIdAndIsRemovedIsFalse(Long id);
    Boolean existsByIdAndIsRemovedIsFalse(Long id);
}
