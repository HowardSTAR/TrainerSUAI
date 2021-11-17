package com.suai.trainersuai.persistence.repositories;

import com.suai.trainersuai.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByIdRegistration(long id);

}
