package com.example.track.entity;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "passwd")
	private String password;
	
	@Column(name = "status_id", columnDefinition = "INT NOT NULL DEFAULT 1")
	private int status;
	
	@Column(name = "transaction_id" , unique = true)
	private String transactionId;
	
	@Column(name = "role_id")
	private String role;
	
	
	
}
