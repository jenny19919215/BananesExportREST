package com.bananes;


import com.bananes.model.Destination;
import com.bananes.repo.DestRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate
@ActiveProfiles("test")
public class DestControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private DestRepository mockRepository;

    @Before
    public void init() {
    	Destination dest= new Destination("dd", "tttt", new Integer(71222), "paris", "france");
    	//mockRepository.save(dest);
    }

    @Test
    public void save_Dest_OK() throws Exception {

    	Destination dest= new Destination("dd", "tttt", new Integer(71222), "paris", "france");
        ResponseEntity<String> response = restTemplate.postForEntity("/destinations", dest, String.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
    
    
    @Test
    public void add_DestId_OK() throws JSONException {
    	assertEquals(1, mockRepository.findAll().size());

    }

   

}
