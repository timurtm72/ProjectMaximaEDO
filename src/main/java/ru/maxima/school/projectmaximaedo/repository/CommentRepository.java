package ru.maxima.school.projectmaximaedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxima.school.projectmaximaedo.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository  extends JpaRepository<Comment, Long> {
    List<Comment> findAllByIsRemovedIsFalseOrderByIdAsc();
    Optional<Comment> findCommentByIdIsRemovedIsFalse(Long id);
    Boolean existsByIdIsRemovedIsFalse(Long id);
}
