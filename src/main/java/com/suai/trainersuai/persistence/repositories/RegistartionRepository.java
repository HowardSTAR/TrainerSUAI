package com.suai.trainersuai.persistence.repositories;

import com.suai.trainersuai.persistence.entities.RegistrationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistartionRepository extends JpaRepository<RegistrationUser, Long> {
}
