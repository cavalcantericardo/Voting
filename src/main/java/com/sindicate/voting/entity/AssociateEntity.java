package com.sindicate.voting.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "db_associate")
public class AssociateEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "as_id",
			updatable = false)
	private Long id;
	
	@JsonIgnore
	@OneToMany(mappedBy = "associateEntity")
	private Set<VotesEntity> votesEntity = new HashSet<>();

	@NotBlank(message = "Name not informed, blank or empty")
	@Column(name = "as_name",
			columnDefinition = "TEXT")
	private String name;
	
	@NotBlank(message = "CPF not informed, blank or empty")
	@CPF(message = "Invalid CPF,please send a valid CPF")
	@Column(name = "as_cpf",
			unique = true,
			columnDefinition = "VARCHAR",
			length = 11
			)
	private String cpf;
	
	
	/* CONSTRUCTOR */
	
	public AssociateEntity() {
		
	}
	
	public AssociateEntity( String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
	}
	
	/* GETTERS AND SETTERS */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Set<VotesEntity> getVotesEntity() {
		return votesEntity;
	}

	
}
