package com.sindicate.voting.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.sindicate.voting.entity.VotesEntity;
import com.sindicate.voting.service.VotesService;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/votes")
public class VotesController {
	
	@Autowired
	private VotesService votesService;
	
	@GetMapping
	public List<VotesEntity> findAllVotes(){
		return votesService.findAll();
	}
	
	@GetMapping("/result/{id}")
	public String findTopicId(@PathVariable("id") Long id){
		return votesService.getResult(id);
	}
	
	
	@PostMapping("/associate/{associateId}/topic/{topicId}")
	public VotesEntity saveVotes( 	@Valid
			 						@PathVariable Long associateId,
			 						@PathVariable Long topicId,
			 						@RequestBody VotesEntity votesEntity
	){
		
		return votesService.save(associateId,topicId, votesEntity);
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
