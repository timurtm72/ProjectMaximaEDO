package ru.maxima.school.projectmaximaedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxima.school.projectmaximaedo.model.DocumentField;

public interface DocumentTemplateFieldRepository  extends JpaRepository<DocumentField,Long> {
}
