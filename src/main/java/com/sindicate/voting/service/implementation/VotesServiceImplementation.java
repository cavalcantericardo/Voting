package com.sindicate.voting.service.implementation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sindicate.voting.entity.AssociateEntity;
import com.sindicate.voting.entity.TopicEntity;
import com.sindicate.voting.entity.VotesEntity;
import com.sindicate.voting.repository.VotesRepository;
import com.sindicate.voting.service.AssociateService;
import com.sindicate.voting.service.TopicService;
import com.sindicate.voting.service.VotesService;

@Service
public class VotesServiceImplementation implements VotesService{

	@Autowired
	private final VotesRepository votesRepository;
	
	@Autowired
	private AssociateService associateService;
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public VotesServiceImplementation(VotesRepository votesRepository ) {
		this.votesRepository = votesRepository;
	}
	
	@Override
	public List<VotesEntity> findAll(){
		return votesRepository.findAll();
	}
	
	@Override
	public String getResult(Long topicResultId ){
		
		LocalDateTime now = LocalDateTime.now();
		
		TopicEntity topicEntity = topicService.getReferenceById(topicResultId);
		
		DateTimeFormatter dayDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter hourDateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		
		String dayVoteDeadLine = topicEntity.getVoteDeadLine().format(dayDateTimeFormatter);
		String hourVoteDeadLine = topicEntity.getVoteDeadLine().format(hourDateTimeFormatter);
		
		if(now.isAfter(topicEntity.getVoteDeadLine())) {
			String sql = "SELECT COUNT(*) FROM public.db_vote WHERE topic_id = " +topicResultId+ " and  vote  LIKE '%yes%' ";
			String sql2 = "SELECT COUNT(*) FROM public.db_vote WHERE topic_id = " +topicResultId+ " and  vote  LIKE '%no%' ";
			
			Integer positiveVotes = jdbcTemplate.queryForObject(sql, Integer.class);
			Integer negativeVotes = jdbcTemplate.queryForObject(sql2, Integer.class);
			
			if ( positiveVotes > negativeVotes) {
				return "Resultado: Pauta aprovada pela maioria.\nPauta de tema \""+ topicEntity.getTitle() +"\" obteve "+ positiveVotes+" votos positivos e "+negativeVotes+" votos negativos. ";
			}
			if ( positiveVotes < negativeVotes) {
				return "Resultado: Pauta reprovada pela maioria.\nPauta de tema \""+ topicEntity.getTitle() +"\" obteve "+ positiveVotes+" votos positivos e "+negativeVotes+" votos negativos. ";
			}
			if( positiveVotes.equals(negativeVotes) ) {
				return "Resultado: Empate na votação da pauta.\nPauta de tema \""+ topicEntity.getTitle() +"\" obteve "+ positiveVotes+" votos positivos e "+negativeVotes+" votos negativos. ";
			}
			
		}else {
			return "Votação ainda em andamento, por favor aguarde até as "+ hourVoteDeadLine +" do dia "+ dayVoteDeadLine+"";
		}
		
		return "Resultado da votação do tema \""+ topicEntity.getTitle();
	}

	@Override
	public VotesEntity save(Long associateId, Long topicId ,VotesEntity votesEntity) {
		
		VotesEntity votesEntity2 = votesEntity;
		AssociateEntity associateEntity = associateService.findById(associateId).get();
		TopicEntity topicEntity = topicService.findById(topicId).get();
		
		votesEntity2.assignVote(associateEntity, topicEntity);
		
		LocalDateTime now = LocalDateTime.now();
			
		if(now.isAfter(topicEntity.getVoteDeadLine())) {
			throw new IllegalStateException("Voting already finished");
		}
		
		return votesRepository.save(votesEntity2);
		
	}

	 
	
}
