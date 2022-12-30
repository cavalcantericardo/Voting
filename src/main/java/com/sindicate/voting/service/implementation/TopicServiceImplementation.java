package com.sindicate.voting.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sindicate.voting.entity.TopicEntity;
import com.sindicate.voting.repository.TopicRepository;
import com.sindicate.voting.service.TopicService;

@Service
public class TopicServiceImplementation implements TopicService{

	@Autowired
	private final TopicRepository topicRepository;
	
	public TopicServiceImplementation(TopicRepository topicRepository ) {
		this.topicRepository = topicRepository;
	}
	
	@Override
	public List<TopicEntity> findAll(){
		return topicRepository.findAll();
	}
	
	@Override
	public TopicEntity getReferenceById(Long id) {
		return topicRepository.getReferenceById(id);
	}
	

	@Override
	public Optional<TopicEntity> findById(Long id) {
		return topicRepository.findById(id);
	}

	@Override
	public TopicEntity save(TopicEntity topicEntity) {
		return topicRepository.save(topicEntity);
	}

	@Override
	public TopicEntity update(TopicEntity topicEntity) {
		return topicRepository.save(topicEntity);
	}

	@Override
	public void deleteById(Long id) {
		topicRepository.deleteById(id);
	}
	
 
	
}
