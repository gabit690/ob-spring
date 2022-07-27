package com.gabit.obpractice.controllers;

import com.gabit.obpractice.entities.VideogameEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VideogameControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {

        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);

        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Create a new videogame")
    @Test
    void createTest() {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "name":"Super Mario",
                    "genre":"Platform",
                    "releaseYear":1990
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<VideogameEntity> response = testRestTemplate.exchange("/api/videogames", HttpMethod.POST, request, VideogameEntity.class);

        VideogameEntity result = response.getBody();

        assertEquals(1L, result.getId());

        assertEquals("Super Mario", result.getName());

        assertEquals("Platform", result.getGenre());

        assertEquals(1990, result.getReleaseYear());
    }

    @DisplayName("Check a videogame with id 1")
    @Test
    void findOneByIdTest() {

        ResponseEntity<VideogameEntity> response = testRestTemplate.getForEntity("/api/videogames/1", VideogameEntity.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Find all videogames in database")
    @Test
    void findAllTest() {

        ResponseEntity<VideogameEntity[]> response = testRestTemplate.getForEntity("/api/videogames", VideogameEntity[].class);

        List<VideogameEntity> videogames = Arrays.asList(response.getBody());

        assertNotNull(videogames);

        assertEquals(videogames.size(), 0);
    }

    @DisplayName("Try to update a videogame with id 1")
    @Test
    void updateTest() {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "id": 1,
                    "name":"Super Mario 2",
                    "genre":"Platform",
                    "releaseYear":1992
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<VideogameEntity> response = testRestTemplate.exchange("/api/videogames", HttpMethod.PUT, request, VideogameEntity.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Try to delete a videogame with id 10")
    @Test
    void deleteTest() {

        ResponseEntity<VideogameEntity> response = testRestTemplate.exchange("/api/videogames/10", HttpMethod.DELETE, null, VideogameEntity.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Delete all videogames in database")
    @Test
    void deleteAllTest() {

        ResponseEntity<VideogameEntity> response = testRestTemplate.exchange("/api/videogames", HttpMethod.DELETE, null, VideogameEntity.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}