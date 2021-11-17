package com.suai.trainersuai.persistence.repositories;

import com.suai.trainersuai.persistence.entities.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserRating, Long> {

    UserRating findByIdRegistration(long id);

}
