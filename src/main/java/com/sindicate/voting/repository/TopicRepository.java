package com.sindicate.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sindicate.voting.entity.TopicEntity;

@Repository
public interface TopicRepository extends JpaRepository< TopicEntity, Long>{

}
