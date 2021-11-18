package com.suai.trainersuai.persistence.repositories;

import com.suai.trainersuai.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findById(long id);

}
