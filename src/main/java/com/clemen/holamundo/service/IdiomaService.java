package com.clemen.holamundo.service;

import com.clemen.holamundo.entity.Idioma;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface IdiomaService {

    List<Idioma> verIdiomas();

    Idioma verIdioma(Long id);

    Idioma guardarIdioma(Idioma idioma);

    Idioma actualizarIdioma(Idioma idioma);

    boolean eliminarIdioma(Long id);

    void eliminarTodosIdiomas();

    boolean existeIdioma(Long id);
}
