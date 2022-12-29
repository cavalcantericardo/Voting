package com.sindicate.voting.controller;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sindicate.voting.entity.AssociateEntity;
import com.sindicate.voting.entity.TopicEntity;
import com.sindicate.voting.entity.VotesEntity;
import com.sindicate.voting.repository.AssociateRepository;
import com.sindicate.voting.repository.TopicRepository;
import com.sindicate.voting.repository.VotesRepository;

@RestController
@RequestMapping("/votes")
public class VotesController {
	
	@Autowired
	private VotesRepository votesRepository;
	
	@Autowired
	private AssociateRepository associateRepository;
	
	@Autowired
	private TopicRepository topicRepository;

	@GetMapping
	public List<VotesEntity> findAllVotes(){
		return votesRepository.findAll();
	}
	

	/*Coletar votos de um mesmo id de topico*/
	
	@PostMapping("/associate/{associateId}/topic/{topicId}")
	public VotesEntity saveVotes( 	
			 						@PathVariable Long associateId,
			 						@PathVariable Long topicId,
			 						@RequestBody VotesEntity votesEntity
	){
		VotesEntity votesEntity2 = votesEntity;
		AssociateEntity associateEntity = associateRepository.findById(associateId).get();
		TopicEntity topicEntity = topicRepository.findById(topicId).get();
		
		votesEntity2.assignVote(associateEntity, topicEntity);
		
		LocalDateTime now = LocalDateTime.now();
		
		if(now.isAfter(topicEntity.getVoteDeadLine())) {
			throw new IllegalStateException("Votação já finalizada");
		}
		
		return votesRepository.save(votesEntity2);
	}
	
	
}
