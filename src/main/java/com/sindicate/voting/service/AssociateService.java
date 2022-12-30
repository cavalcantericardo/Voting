package com.sindicate.voting.service;

import java.util.List;
import java.util.Optional;

import com.sindicate.voting.entity.AssociateEntity;

public interface AssociateService {

	List<AssociateEntity> findAll();
	Optional<AssociateEntity> findById(Long id);
	AssociateEntity save(AssociateEntity associateEntity);
	AssociateEntity update(AssociateEntity associateEntity);
	void deleteById(Long id);
	
}
