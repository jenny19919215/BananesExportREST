package com.bananes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bananes.model.Destination;

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

}
