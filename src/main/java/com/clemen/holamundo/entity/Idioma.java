package com.clemen.holamundo.entity;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "IDIOMAS")
public class Idioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Clave autoincremental tipo Long")
    private Long id;
    @ApiModelProperty("Nombre del Idioma")
    private String nombre;
    @ApiModelProperty("Hola mundo, expresado en el idioma")
    private String saludo;

    public Idioma() {
    }

    public Idioma(Long id,String nombre, String saludo) {
        this.id=id;
        this.nombre = nombre;
        this.saludo = saludo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String mensaje) {
        this.nombre = mensaje;
    }

    public String getSaludo() {
        return saludo;
    }

    public void setSaludo(String saludo) {
        this.saludo = saludo;
    }

    @Override
    public String toString() {
        return saludo;
    }
}
