package com.suai.trainersuai.repositories;

import com.suai.trainersuai.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findById(long id);

    User deleteById(long id);
}