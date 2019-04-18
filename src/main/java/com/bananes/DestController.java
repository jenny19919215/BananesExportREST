package com.bananes;

import com.bananes.model.Destination;
import com.bananes.repo.BookRepository;
import com.bananes.repo.DestRepository;
import com.mkyong.error.BookNotFoundException;
import com.mkyong.error.BookUnSupportedFieldPatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

@RestController("/destinations")
public class DestController {
    
    @Autowired
    private DestService destService;
    
    // Find
    @GetMapping
    List<Destination> findAll() {
        return destService.findAll();
    }

    // Save
    @PostMapping
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    synchronized Destination newDest(@Valid @RequestBody Destination newDest) {
    	/*Long id = repository.count() + 1;
    	newDest.setId(id);*/
    	/*List<Destination> l = repository.findDestbyCrits(newDest.getName(),newDest.getAddress(),newDest.getPostCode(),newDest.getCity(),newDest.getCountry());
    	Destination dest = null;
    	if(!CollectionUtils.isEmpty(l) ){	
    		dest = l.stream().filter(d -> newDest.equals(d)).findFirst().orElse(null);	
    	}
    	if(dest ==null){
    		return repository.save(newDest);
    	}else return dest;
    	*/
    	return destService.save(newDest);
    	
       
    }

    /*// Find
    @GetMapping("/books/{id}")
    Book findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }*/

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
