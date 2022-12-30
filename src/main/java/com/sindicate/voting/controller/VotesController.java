package com.sindicate.voting.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import com.sindicate.voting.entity.AssociateEntity;
import com.sindicate.voting.entity.TopicEntity;
import com.sindicate.voting.entity.VotesEntity;
import com.sindicate.voting.repository.AssociateRepository;
import com.sindicate.voting.repository.TopicRepository;
import com.sindicate.voting.repository.VotesRepository;

import jakarta.validation.Valid;

import org.springframework.jdbc.core.JdbcTemplate;

@RestController
@RequestMapping("/votes")
public class VotesController {
	
	@Autowired
	private VotesRepository votesRepository;
	
	@Autowired
	private AssociateRepository associateRepository;
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@GetMapping
	public List<VotesEntity> findAllVotes(){
		return votesRepository.findAll();
	}
	
	@GetMapping("/result/{id}")
	public String findTopicId(@PathVariable("id") Long id){
		LocalDateTime now = LocalDateTime.now();
	
		TopicEntity topicEntity = topicRepository.getReferenceById(id);
		
		DateTimeFormatter dayDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter hourDateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		
		String dayVoteDeadLine = topicEntity.getVoteDeadLine().format(dayDateTimeFormatter);
		String hourVoteDeadLine = topicEntity.getVoteDeadLine().format(hourDateTimeFormatter);
		
		if(now.isAfter(topicEntity.getVoteDeadLine())) {
			String sql = "SELECT COUNT(*) FROM public.db_vote WHERE topic_id = " +id+ " and  vote  LIKE '%yes%' ";
			String sql2 = "SELECT COUNT(*) FROM public.db_vote WHERE topic_id = " +id+ " and  vote  LIKE '%no%' ";
			
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
	
	
	@PostMapping("/associate/{associateId}/topic/{topicId}")
	public VotesEntity saveVotes( 	@Valid
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
			throw new IllegalStateException("Voting already finished");
		}
		
		return votesRepository.save(votesEntity2);
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
