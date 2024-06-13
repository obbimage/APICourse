package com.app.course.repository;

import com.app.course.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationRepository  extends JpaRepository<VerificationToken,Long> {
    Optional<VerificationToken> findByToken(String token);
}
