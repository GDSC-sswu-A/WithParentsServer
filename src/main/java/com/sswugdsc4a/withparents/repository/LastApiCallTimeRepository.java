package com.sswugdsc4a.withparents.repository;

import com.sswugdsc4a.withparents.entity.LastApiCallTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LastApiCallTimeRepository extends JpaRepository<LastApiCallTime, Long> {
}
