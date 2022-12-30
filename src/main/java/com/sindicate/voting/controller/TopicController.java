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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sindicate.voting.entity.TopicEntity;
import com.sindicate.voting.service.TopicService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topic")
public class TopicController {
	
	@Autowired
	private TopicService topicService;

	@GetMapping
	public List<TopicEntity> findAllTopic(){
		return topicService.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<TopicEntity> findTopicId(@PathVariable("id") Long id){
		return topicService.findById(id);
	}
	
	@PostMapping
	public TopicEntity saveTopic(@RequestBody @Valid TopicEntity topicEntity) {
		return topicService.save(topicEntity);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTopic(@PathVariable("id") Long id) {
		topicService.deleteById(id);
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
