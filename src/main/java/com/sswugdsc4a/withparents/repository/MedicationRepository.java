package com.sswugdsc4a.withparents.repository;

import com.sswugdsc4a.withparents.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
