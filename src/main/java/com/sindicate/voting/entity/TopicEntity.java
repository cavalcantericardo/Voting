package com.sindicate.voting.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "db_topic")
public class TopicEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "to_id",
			updatable = false)
	private Long id;
	

	@JsonIgnore
	@OneToMany(mappedBy = "topicEntity")
	private Set<VotesEntity> votesEntity = new HashSet<>();

	@NotBlank(message = "Title not informed, blank or empty")
	@Column(name = "to_title",
			nullable = false,
			columnDefinition = "TEXT")
	private String title;

	
	@NotBlank@NotBlank(message = "Description not informed, blank or empty")
	@Column(name = "to_description",
			nullable = false,
			columnDefinition = "TEXT")
	private String description;


	  @Column
	  private LocalDateTime createdTimestamp;
	  
	  @Column
	  private LocalDateTime voteDeadLine;
	  
	  @Column
	  private int timerMinutes = 1;
	
	/* CONSTRUCTOR */
	
	public TopicEntity() {
		
	}
	
	public TopicEntity(Long id, String description, int timerMinutes ) {
		this.id = id;
		this.description = description;
		this.timerMinutes = timerMinutes;
	}
	
	@PrePersist
	  public void setCreationDateTime() {
	    this.createdTimestamp = LocalDateTime.now();
	    this.voteDeadLine = LocalDateTime.now().plusMinutes(this.timerMinutes);
	  }

	
	/* GETTERS AND SETTERS*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	 public LocalDateTime getCreatedTimestamp() {
		return createdTimestamp;
	}
	 
	 public LocalDateTime getVoteDeadLine() {
		return voteDeadLine;
	}

	public void setVoteDeadLine(LocalDateTime voteDeadLine) {
		this.voteDeadLine = voteDeadLine;
	}
	
	public int getTimerMinutes() {
		return timerMinutes;
	}

	public void setTimerMinutes(int timerMinutes) {
		this.timerMinutes = timerMinutes;
	}

	
	public Set<VotesEntity> getVotesEntity() {
		return votesEntity;
	}
	
	
}
