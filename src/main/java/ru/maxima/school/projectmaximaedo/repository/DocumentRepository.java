package ru.maxima.school.projectmaximaedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxima.school.projectmaximaedo.enums.DocumentType;
import ru.maxima.school.projectmaximaedo.model.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document,Long> {
    List<Document> findAllByIsRemovedIsFalseOrderByIdAsc();
    Optional<Document> findDocumentByIdAndIsRemovedIsFalse(Long id);

    Boolean existsByIdAndIsRemovedIsFalse(Long id);
    List<Document> findAllByDocumentTypeOrderByRegistryNumberAsc(DocumentType documentType);
}
