package ru.maxima.school.projectmaximaedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxima.school.projectmaximaedo.model.Partner;

import java.util.List;
import java.util.Optional;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    List<Partner> findAllByIsRemovedIsFalseOrderByIdAsc();
    Optional<Partner> findPartnerByIdIsRemovedIsFalse(Long id);
    Boolean existsByIdIsRemovedIsFalse(Long id);
}
