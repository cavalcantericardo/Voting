package com.sindicate.voting.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sindicate.voting.entity.AssociateEntity;
import com.sindicate.voting.repository.AssociateRepository;

@RestController
@RequestMapping("/associate")
public class AssociateController {
	
	@Autowired
	private AssociateRepository associateRepository;

	@GetMapping
	public List<AssociateEntity> findAllAssociate(){
		return associateRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<AssociateEntity> findAssociateId(@PathVariable("id") Long id){
		return associateRepository.findById(id);
	}
	
	@PostMapping() 
	public AssociateEntity saveAssociate(@RequestBody AssociateEntity associateEntity) {
		return associateRepository.save(associateEntity);
	}
	
	@PutMapping
	public AssociateEntity updateAssociate(@RequestBody AssociateEntity associateEntity) {
		return associateRepository.save(associateEntity);
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteAssociate(@PathVariable("id") Long id) {
		associateRepository.deleteById(id);
	}
	
}
