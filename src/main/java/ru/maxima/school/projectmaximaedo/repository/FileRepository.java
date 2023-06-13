package ru.maxima.school.projectmaximaedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxima.school.projectmaximaedo.model.AttachedFile;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<AttachedFile, Long> {
    List<AttachedFile> findAllByIsRemovedIsFalseOrderByIdAsc();
    Optional<AttachedFile> findFileById(Long id);
}
