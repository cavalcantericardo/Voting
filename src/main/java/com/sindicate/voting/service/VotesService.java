package com.sindicate.voting.service;

import java.util.List;
import com.sindicate.voting.entity.VotesEntity;

public interface VotesService {

	List<VotesEntity> findAll();
	/*Optional<TopicEntity> findById(Long id);*/
	/*TopicEntity update(TopicEntity topicEntity);
	void deleteById(Long id);
	*/
	String getResult(Long topicResultId);
	VotesEntity save(Long associateId, Long topicId, VotesEntity votesEntity);
}
