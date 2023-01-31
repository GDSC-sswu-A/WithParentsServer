package com.sswugdsc4a.withparents.repository;

import com.sswugdsc4a.withparents.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

    List<Medication> findAllByUserId(Long userId);

}
