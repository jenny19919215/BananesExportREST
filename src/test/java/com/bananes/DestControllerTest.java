package com.bananes;


import com.bananes.model.Destination;
import com.bananes.repo.DestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    
    @MockBean
    private DestService mockService;
    
    @Before
    public void init() {
    	Destination dest= new Destination("dd", "tttt", new Integer(71222), "paris", "france");
    	dest.setId(1L);
    	when(mockRepository.findById(1L)).thenReturn(Optional.of(dest));
    }
    
    @Test
    public void find_allDest_OK() throws Exception {

        List<Destination> dests = Arrays.asList(
                new Destination("dddd", "tttt", new Integer(71222), "paris", "france"),
        		new Destination("tttt", "tttt", new Integer(71222), "paris", "france"));

        when(mockService.findAll()).thenReturn(dests);

        String expected = om.writeValueAsString(dests);

        ResponseEntity<String> response = restTemplate.getForEntity("/destinations", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(), false);

        verify(mockService, times(1)).findAll();
    }
    

    @Test
    public void save_Dest_OK() throws Exception {

    	Destination dest= new Destination("dd", "tttt", new Integer(71222), "paris", "france");
    	when(mockService.save(any(Destination.class))).thenReturn(dest);
    	
    	String expected = om.writeValueAsString(dest);
        ResponseEntity<String> response = restTemplate.postForEntity("/destinations", dest, String.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(), false);
        //assertEquals(dest.toString(),response.getBody());
    }

}
