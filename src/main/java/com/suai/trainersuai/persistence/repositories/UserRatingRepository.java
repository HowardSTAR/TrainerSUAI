package com.suai.trainersuai.persistence.repositories;

import com.suai.trainersuai.persistence.entities.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRatingRepository extends JpaRepository<UserRating, Long> {

}
