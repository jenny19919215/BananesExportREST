package com.bananes.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bananes.model.Command;
import com.bananes.model.Destination;

public interface CommandRepository extends JpaRepository<Command, Long> {
	
	@Query("SELECT c FROM Command c JOIN FETCH c.dest d WHERE d.id = :id")
	List<Command> findCommandbyDest(@Param("id")Long id);
	
	
}
