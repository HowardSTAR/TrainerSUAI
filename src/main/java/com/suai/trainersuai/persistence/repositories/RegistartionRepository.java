package com.suai.trainersuai.persistence.repositories;

import com.suai.trainersuai.persistence.entities.RegistrationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RegistartionRepository extends JpaRepository<RegistrationUser, Long> {

    RegistrationUser findByEmail(String email);

    RegistrationUser findById(long id);

}
