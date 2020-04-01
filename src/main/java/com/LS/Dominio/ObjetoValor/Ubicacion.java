/*
 * Ubicacion.java 1.0 30/03/2020
 */

/**
 * Esta clase reprensenta el objeto valor Ubicacion
 *
 * @author Eduardo Gimeno
 * @version 1.0, 01/04/2020
 */

package com.LS.Dominio.ObjetoValor;

import javax.validation.constraints.NotNull;

public class Ubicacion {

    @NotNull
    private String edificio;

    @NotNull
    private int planta;

    public Ubicacion(String edificio, int planta) {
        this.edificio = edificio;
        this.planta = planta;
    }

    public String getEdificio() {
        return this.edificio;
    }

    public int getPlanta() {
        return this.planta;
    }
}