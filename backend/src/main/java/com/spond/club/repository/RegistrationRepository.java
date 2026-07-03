package com.spond.club.repository;

import com.spond.club.entity.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity, Long>  {
    boolean existsByForm_IdAndEmail(Long formId, String email);
}
