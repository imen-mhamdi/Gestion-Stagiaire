 package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.classes.Stage;


@Repository
public interface StageRepository extends JpaRepository<Stage,Long> {

}
