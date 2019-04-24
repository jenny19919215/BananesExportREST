package com.bananes;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bananes.error.DaysDiffException;
import com.bananes.error.QuantityIlegalException;
import com.bananes.model.Command;
import com.bananes.model.Destination;
import com.bananes.repo.CommandRepository;

@RestController
public class CommandController {

    @Autowired
    private CommandRepository repository;
    
    @Autowired
    private DestService destService;

    // Find
    @GetMapping("/commands")
    List<Command> findAll() {
        return repository.findAll();
    }

    // Save
    @PostMapping("/commands")
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    Command newCommand(@Valid @RequestBody Command newco){
    	if(newco.getQuantity() % 25 != 0 ){
    		throw new QuantityIlegalException(newco.getQuantity());	
    	}
    	LocalDate today = LocalDate.now();
    	LocalDate deliverDate = newco.getDeliverDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	long days = ChronoUnit.DAYS.between(today, deliverDate);
    	if(days < 7l){
    		throw new DaysDiffException();
    	}
 
    	Destination des = newco.getDest();
    	Destination des1 = null;
    	if(des.getId() != null){
    		des1 = destService.findDestbyId(des.getId());
    	}
    	if(des1 == null){
    		des.setId(null);
    		des1 = destService.save(des);	
    	}
    	newco.setDest(des1);
    	newco.setPrice((float) (newco.getQuantity()*2.5));
    	return repository.save(newco);
    }

    // Find
    @GetMapping("/commands/dest/{id}")
    List<Command> findCommandByDest(@PathVariable Long id) {
        return repository.findCommandbyDest(id);
               // .orElseThrow(() -> new BookNotFoundException(id));
    }


}
