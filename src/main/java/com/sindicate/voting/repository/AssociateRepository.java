package com.sindicate.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sindicate.voting.entity.AssociateEntity;

@Repository
public interface AssociateRepository extends JpaRepository< AssociateEntity, Long> {

}
