package com.example.demo.classes;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Stage")
public class Stage implements Serializable {
  
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@Column(name = "nom", length =60, nullable = false)
    private String nom ;
	@Column(name = "typeStage", length =60, nullable = false)
    private String typeStage;
	@Column(name = "niveau", length =60, nullable = false)
    private String niveau;
	@Column(name = "duree", length =60, nullable = false)
    private String duree;
	@Column(name = "sujet", length =60, nullable = false)
    private String sujet;
	@Column(name = "departement", length =60, nullable = false)
    private String departement;
	@Column(name = "description", length =60, nullable = false)
    private String description;
    private Date dateDebutStage;
    private Date dateFinStage ;

    @JsonIgnore
    @ManyToMany(mappedBy = "stagiaire_stage")
    private Set<Stagiaire> stagiaire_stage = new HashSet<>();

	}
