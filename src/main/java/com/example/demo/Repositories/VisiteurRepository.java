package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.classes.Visiteur;

public interface VisiteurRepository extends JpaRepository<Visiteur,Integer> {
//	public Visiteur findByEmail(String email);
	// public Visiteur findByEmailAndPassword (String tempEmail,String tempPassword);

}
