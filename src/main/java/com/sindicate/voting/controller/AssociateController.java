package com.sindicate.voting.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sindicate.voting.entity.AssociateEntity;

import com.sindicate.voting.service.AssociateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/associate")
public class AssociateController {
	
	@Autowired
	private AssociateService associateService;

	@GetMapping
	public List<AssociateEntity> findAllAssociate(){
		return associateService.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<AssociateEntity> findAssociateId(@PathVariable("id") Long id){
		return associateService.findById(id);
	}
	
	@PostMapping() 
	public AssociateEntity saveAssociate(@RequestBody @Valid AssociateEntity associateEntity) {
		return associateService.save(associateEntity);
	}
	
	@PutMapping
	public AssociateEntity updateAssociate(@RequestBody @Valid AssociateEntity associateEntity) {
		return associateService.save(associateEntity);
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteAssociate(@PathVariable("id") Long id) {
		associateService.deleteById(id);
	}
	
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
	
}
