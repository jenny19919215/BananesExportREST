package com.bananes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.bananes.model.Command;
import com.bananes.model.Destination;
import com.bananes.repo.BookRepository;
import com.bananes.repo.CommandRepository;
import com.bananes.repo.DestRepository;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Optional;

@SpringBootApplication
public class StartBookApplication {

    // start everything
    public static void main(String[] args) {
        SpringApplication.run(StartBookApplication.class, args);
    }

    // run this only on profile 'demo', avoid run this in test
    @Profile("demo")
    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {
        return args -> {
            repository.save(new Book("A Guide to the Bodhisattva Way of Life", "Santideva", new BigDecimal("15.41")));
            repository.save(new Book("The Life-Changing Magic of Tidying Up", "Marie Kondo", new BigDecimal("9.69")));
            repository.save(new Book("Refactoring: Improving the Design of Existing Code", "Martin Fowler", new BigDecimal("47.99")));
        };
    }
    
    @Profile("demo")
    @Bean
    CommandLineRunner initDestDatabase(DestRepository repository) {
        return args -> {
        	Destination des= new Destination("dd", "tttt", new Integer(71222), "paris", "france");
        	//des.setId(repository.count()+1);
            repository.save(des);     
        };
    }
    
    @Profile("demo")
    @Bean
    CommandLineRunner initCommandDatabase(CommandRepository repository, DestRepository destRepo) {
        return args -> {
        	Calendar c = Calendar.getInstance();
            c.set(2020, Calendar.SEPTEMBER, 22, 0, 0, 0);
            Optional<Destination> des = destRepo.findById(4L);
            if(des.isPresent()){
            	Command co = new Command(c.getTime(), 25, des.get());
            	 repository.save(co);     
            }   
        };
    }
    
}