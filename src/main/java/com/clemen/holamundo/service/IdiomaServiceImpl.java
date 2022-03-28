package com.clemen.holamundo.service;


import com.clemen.holamundo.entity.Idioma;
import com.clemen.holamundo.repository.IdiomaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdiomaServiceImpl implements IdiomaService {

    private final Logger log = LoggerFactory.getLogger(IdiomaServiceImpl.class);

    @Autowired
    private IdiomaRepository idiomaRepository;

    @Override
    public List<Idioma> verIdiomas() {
        return idiomaRepository.findAll();
    }

    @Override
    public Idioma verIdioma(Long id) {
        Optional<Idioma> idiomaOpt = idiomaRepository.findById(id);
        return idiomaOpt.orElse(null);
    }

    @Override
    public Idioma guardarIdioma(Idioma idioma) {
        return idiomaRepository.save(idioma);
    }

    @Override
    public Idioma actualizarIdioma(Idioma idioma) {

        return idiomaRepository.save(idioma);
    }

    @Override
    public boolean eliminarIdioma(Long id) {
        if (!idiomaRepository.existsById(id)) {
            return false;
        }
        idiomaRepository.deleteById(id);
        return true;
    }

    @Override
    public void eliminarTodosIdiomas() {
        log.info("Borrando todos los idiomas de la DB.");
        idiomaRepository.deleteAll();
    }

    @Override
    public boolean existeIdioma(Long id) {
        return idiomaRepository.existsById(id);
    }
}
