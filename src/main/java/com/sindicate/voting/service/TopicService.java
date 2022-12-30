package com.sindicate.voting.service;

import java.util.List;
import java.util.Optional;
import com.sindicate.voting.entity.TopicEntity;

public interface TopicService {

	List<TopicEntity> findAll();
	Optional<TopicEntity> findById(Long id);
	TopicEntity save(TopicEntity topicEntity);
	TopicEntity update(TopicEntity topicEntity);
	void deleteById(Long id);
	TopicEntity getReferenceById(Long id);
	
}
