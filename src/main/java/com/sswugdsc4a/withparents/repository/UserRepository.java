package com.sswugdsc4a.withparents.repository;

import com.sswugdsc4a.withparents.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
