package ru.maxima.school.projectmaximaedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxima.school.projectmaximaedo.model.Partner;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
