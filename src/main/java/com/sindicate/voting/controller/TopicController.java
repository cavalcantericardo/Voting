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

import com.sindicate.voting.entity.TopicEntity;
import com.sindicate.voting.repository.TopicRepository;

@RestController
@RequestMapping("/topic")
public class TopicController {
	
	@Autowired
	private TopicRepository topicRepository;

	@GetMapping
	public List<TopicEntity> findAllTopic(){
		return topicRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<TopicEntity> findTopicId(@PathVariable("id") Long id){
		return topicRepository.findById(id);
	}
	
	@PostMapping
	public TopicEntity saveTopic(@RequestBody TopicEntity topicEntity) {
		return topicRepository.save(topicEntity);
	}
	
	@PutMapping
	public TopicEntity updateTopic(@RequestBody TopicEntity topicEntity) {
		return topicRepository.save(topicEntity);
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteTopic(@PathVariable("id") Long id) {
		topicRepository.deleteById(id);
	}
	
}
