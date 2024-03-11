package com.example.demo.Repositories;

import com.example.demo.classes.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface StagiaireRepository  extends JpaRepository<Stagiaire, Long> {

}
