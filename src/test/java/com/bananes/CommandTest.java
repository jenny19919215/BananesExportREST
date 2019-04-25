package com.bananes;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.bananes.model.Command;
import com.bananes.model.Destination;
import com.bananes.repo.CommandRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate
@ActiveProfiles("test")
public class CommandTest {
	private static final ObjectMapper om = new ObjectMapper();
	 @Autowired
	 private TestRestTemplate restTemplate;

	   
	 @MockBean
	 private CommandRepository mockRepository;
	 
	 
	@Test
	public void save_command_OK() throws Exception {
		Calendar c = Calendar.getInstance();
        c.set(2020, Calendar.SEPTEMBER, 22, 0, 0, 0);
        Destination dest= new Destination("dd", "tttt", new Integer(71222), "paris", "france");
        dest.setId(1L);
        Command newCommand = new Command(c.getTime(), 50, dest);
		when(mockRepository.save(any(Command.class))).thenReturn(newCommand);

		String expected = om.writeValueAsString(newCommand);

		ResponseEntity<String> response = restTemplate.postForEntity("/commands", newCommand, String.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);

		verify(mockRepository, times(1)).save(any(Command.class));

	}
	 
	 
	 
}
