package com.example.demo.classes;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Stagiaire")
@NoArgsConstructor
@AllArgsConstructor
public class Stagiaire {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String email;
    private String userName;
    private String password;
    private String role;
    private String specialite;
    private String telephone;
    private Date datenaissance;
    private String cin;

	@ManyToMany
	@JoinTable(name = "stagiaire_stage",
	joinColumns = @JoinColumn(name = "stagiaire_id"),
	inverseJoinColumns = @JoinColumn(name = "stage_id"))
	private Set<Stage> stagiaire_stage = new HashSet<>();








    



	public void setRole(String role) {
		this.role = "stagiaire";
	}








}
