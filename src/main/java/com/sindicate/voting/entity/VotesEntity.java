package com.sindicate.voting.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "db_vote",
	   uniqueConstraints = { @UniqueConstraint(name = "UniqueAssociateAndVote",
	   										   columnNames = { "associate_id", "topic_id"})}
	  )
public class VotesEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="associate_id", referencedColumnName = "as_id")
    private AssociateEntity associateEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="topic_id", referencedColumnName = "to_id")
    private TopicEntity topicEntity;
    
    @NotBlank(message = "Vote not informed, blank or empty")
    @Pattern(regexp = "^(?:yes|no)$",message = "Invalid vote, please send a vote as \"yes\" or \"no\"")
    @Column(name="vote",nullable=false, updatable = false)
    private String vote;
    
	@Column
	private LocalDateTime createdTimestamp;
    
/* CONSTRUCTOR*/	  
		 @PrePersist
		  public void setCreationDateTime() {
		    this.createdTimestamp = LocalDateTime.now();
		  }
		
/* GETTERS AND SETTERS*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVote() {
		return vote;
	}

	public void setVote(String vote) {
		this.vote = vote;
	}

	public void assignVote(AssociateEntity associateEntity, TopicEntity topicEntity) {
		this.associateEntity = associateEntity;
		this.topicEntity = topicEntity;
	}
	
	
}
