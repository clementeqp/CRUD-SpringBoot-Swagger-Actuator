package com.clemen.holamundo.controller;


import com.clemen.holamundo.entity.Idioma;
import com.clemen.holamundo.service.IdiomaService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
public class IdiomaController {

    /**
     * url base
     * http://localhost:8080/api/v1/idiomas
     */
    private final String URL = "/api/v1/idiomas";
    private final Logger log = LoggerFactory.getLogger(IdiomaController.class);

    @Autowired
    private IdiomaService idiomaService;


    //Creacion de Idiomas pasndo parametros por url
    @PostMapping(URL + "/{nombre}/{saludo}")
    @ApiIgnore // ignorar este método para que no aparezca en la documentación de la api Swagger
    public Idioma crearIdioma(@PathVariable String nombre, @PathVariable String saludo) {
        log.info("Guardando Idioma.");
        //System.out.println("Entrada POST");
        return idiomaService.guardarIdioma(new Idioma(null, nombre, saludo));
    }
    /**
     * Creacion de Idioma pasando datos en el body como un JSON
     */
    @PostMapping(URL )
    public ResponseEntity<Idioma> creaIdioma(
            @RequestBody Idioma idioma,
            @RequestHeader HttpHeaders head) {

        log.info(String.valueOf(head.get("User-Agent")));
        System.out.println(head.get("User-Agent"));

        if(idioma.getId()!=null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(idiomaService.guardarIdioma(idioma));
    }

    /**
     * Actualizar Idioma por parametro en url
     */
    @PutMapping(URL + "/{id}/{nombre}/{saludo}")
    @ApiIgnore // ignorar este método para que no aparezca en la documentación de la api Swagger
    public ResponseEntity<Idioma> actualizarIdioma(
            @PathVariable Long id,
            @PathVariable String nombre,
            @PathVariable String saludo) {
        if (idiomaService.existeIdioma(id)) {
            Idioma idioma = idiomaService.verIdioma(id);
            idioma.setNombre(nombre);
            idioma.setSaludo(saludo);
            return ResponseEntity.ok(idiomaService.actualizarIdioma(idioma));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Actualizar Idioma por JSON en body
     */
    @PutMapping(URL)
    public ResponseEntity<Idioma> actualizaIdioma(
            @RequestBody Idioma idioma) {
        if (idiomaService.existeIdioma(idioma.getId()))
            return ResponseEntity.ok(idiomaService.actualizarIdioma(idioma));

        //Devolver mensaje de error
        return ResponseEntity.notFound().build();

    }

    //Ver idioma dado un id
    @GetMapping(URL + "/{id}")
    @ApiOperation("Buscr un idioma por PK, id")
    public ResponseEntity<Idioma> obtenerIdioma(@PathVariable Long id) {
        if (idiomaService.existeIdioma(id))
            return ResponseEntity.ok(idiomaService.verIdioma(id));

        return ResponseEntity.notFound().build();

    }

    // Ver todos los idiomas en BD
    @GetMapping(URL)
    public ResponseEntity<List<Idioma>> obtenerIdiomas() {
        return ResponseEntity.ok(idiomaService.verIdiomas());
    }

    // Borrar un idioma dado un id
    @DeleteMapping(URL + "/{id}")
    public ResponseEntity<Idioma> eliminarIdioma(@PathVariable Long id) {
        if (!idiomaService.existeIdioma(id)) {
            log.warn("El idioma no existe.");
            return ResponseEntity.notFound().build();
        }
        idiomaService.eliminarIdioma(id);
        return ResponseEntity.noContent().build();
    }

    // Borrar todos los idiomas
    @DeleteMapping(URL)
    public ResponseEntity<Idioma> eliminarTodosIdiomas() {
        idiomaService.eliminarTodosIdiomas();
        return ResponseEntity.noContent().build();
    }
}

