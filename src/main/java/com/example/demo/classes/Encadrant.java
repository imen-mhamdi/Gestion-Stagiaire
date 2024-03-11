package com.example.demo.classes;
import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Encadrant")
@Entity
@Data
public class Encadrant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		private String email;
	    private String userName;
	    private String password;
	    private String role = "encadarnt";
	    private String specialite;
	    private String telephone;
	    private Date datenaissance;
	    private String cin;
		@ManyToOne
		@JoinColumn(name = "stagiaire_id", referencedColumnName = "id")
		private Stagiaire stagiaire;
	    

}