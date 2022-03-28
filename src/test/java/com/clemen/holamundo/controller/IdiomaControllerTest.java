package com.clemen.holamundo.controller;

import com.clemen.holamundo.entity.Idioma;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Para probar el controlador
class IdiomaControllerTest {

    private final String URL = "/api/v1/idiomas";
    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    // Spring generara un puerto y lo inyectara aqui
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {

        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        restTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Comprobar metodo POST de IdiomaController desde el Test")
    @Test
    void creaIdioma() {
        // Hay que crear el JSON y enviarlo
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        String json = """
                        {
                            "nombre":"Test",
                            "saludo":"Hola, desde el test"
                        }
                        """;
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        //ResponseEntity<Idioma> response = restTemplate.postForEntity(URL, request, Idioma.class);
        ResponseEntity<Idioma> response = restTemplate.exchange(URL, HttpMethod.POST, request, Idioma.class);
        Idioma result = response.getBody();

        //Test
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test", result.getNombre());

        assertTrue(result.getId() > 0);
        System.out.println("Idioma creado: " + result.getId() + " - " + result.getNombre() + " - " + result.getSaludo());
        // Si conocemos el Id que le toca
        //assertEquals(21L, result.getId());

    }

    @Test
    void obtenerIdiomaIdExistente() {
        ResponseEntity<Idioma> response =
                restTemplate.getForEntity("/api/v1/idiomas/7", Idioma.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void obtenerIdiomaIdNoExistente() {
        ResponseEntity<Idioma> response =
                restTemplate.getForEntity("/api/v1/idiomas/45", Idioma.class);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void obtenerIdiomas() {
        // Es una respuesta http , en el body estaran los idiomas
        ResponseEntity<Idioma[]> response =
        restTemplate.getForEntity("/api/v1/idiomas", Idioma[].class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Idioma> idiomas = Arrays.asList(response.getBody());
        System.out.println(idiomas.size());
        //assertEquals(16, idiomas.size());

    }
}