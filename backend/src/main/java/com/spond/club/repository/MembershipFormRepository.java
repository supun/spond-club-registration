package com.spond.club.repository;

import com.spond.club.entity.MembershipFormEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembershipFormRepository extends JpaRepository<MembershipFormEntity, Long> {
    @EntityGraph(attributePaths = "memberTypes")
    Optional<MembershipFormEntity> findByFormId(String formId);
}
