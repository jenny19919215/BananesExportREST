package com.bananes;

import com.bananes.model.Command;
import com.bananes.model.Destination;
import com.bananes.repo.BookRepository;
import com.bananes.repo.CommandRepository;
import com.bananes.repo.DestRepository;
import com.mkyong.error.BookNotFoundException;
import com.mkyong.error.BookUnSupportedFieldPatchException;
import com.mkyong.error.DaysDiffException;
import com.mkyong.error.QuantityIlegalException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

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
    	/*Calendar today = Calendar.getInstance();new Date();
    	today.set(Calendar.HOUR_OF_DAY, 0);
    	long diff = newco.getDeliverDate().getTime() - today.getTimeInMillis()/ (24 * 60 * 60 * 1000);*/
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

   /* // Save or update
    @PutMapping("/books/{id}")
    Book saveOrUpdate(@RequestBody Book newBook, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setName(newBook.getName());
                    x.setAuthor(newBook.getAuthor());
                    x.setPrice(newBook.getPrice());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return repository.save(newBook);
                });
    }

    // update author only
    @PatchMapping("/books/{id}")
    Book patch(@RequestBody Map<String, String> update, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {

                    String author = update.get("author");
                    if (!StringUtils.isEmpty(author)) {
                        x.setAuthor(author);

                        // better create a custom method to update a value = :newValue where id = :id
                        return repository.save(x);
                    } else {
                        throw new BookUnSupportedFieldPatchException(update.keySet());
                    }

                })
                .orElseGet(() -> {
                    throw new BookNotFoundException(id);
                });

    }

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }*/

}
