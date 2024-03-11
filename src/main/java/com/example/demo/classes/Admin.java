package com.example.demo.classes;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Admin{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String userName;
	private String password;

	private  String role ;


	@ManyToOne
	@JoinColumn(name = "stage_id", referencedColumnName = "id")
	private Stage stage;

	@ManyToOne
	@JoinColumn(name = "stagiaire_id", referencedColumnName = "id")
	private Stagiaire stagiaire;

	@ManyToOne
	@JoinColumn(name = "encadrant_id", referencedColumnName = "id")
	private Encadrant encadrant;







	

}
