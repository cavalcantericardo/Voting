package com.sindicate.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sindicate.voting.entity.VotesEntity;

@Repository
public interface VotesRepository extends JpaRepository< VotesEntity, Long> {

}
