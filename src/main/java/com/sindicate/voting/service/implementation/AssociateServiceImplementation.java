package com.sindicate.voting.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sindicate.voting.entity.AssociateEntity;
import com.sindicate.voting.repository.AssociateRepository;
import com.sindicate.voting.service.AssociateService;

@Service
public class AssociateServiceImplementation implements AssociateService{

	@Autowired
	private final AssociateRepository associateRepository;
	
	public AssociateServiceImplementation(AssociateRepository associateRepository ) {
		this.associateRepository = associateRepository;
	}
	
	@Override
	public List<AssociateEntity> findAll(){
		return associateRepository.findAll();
	}

	@Override
	public Optional<AssociateEntity> findById(Long id) {
		return associateRepository.findById(id);
	}

	@Override
	public AssociateEntity save(AssociateEntity associateEntity) {
		return associateRepository.save(associateEntity);
	}

	@Override
	public AssociateEntity update(AssociateEntity associateEntity) {
		return associateRepository.save(associateEntity);
	}

	@Override
	public void deleteById(Long id) {
		associateRepository.deleteById(id);
	}
	
 
	
}
