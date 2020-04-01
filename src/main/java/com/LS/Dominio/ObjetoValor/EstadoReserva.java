/*
 * EstadoReserva.java 1.0 30/03/2020
 */

/**
 * @author Gonzalo Berné
 * @author Eduardo Gimeno
 * @version 2.0, 01/04/2020
 */

package com.LS.Dominio.ObjetoValor;

public enum EstadoReserva {
    ACEPTADA("aceptada"),
    RECHAZADA("rechazada"),
    PENDIENTE("pendiente");

    private String estado;
 
    EstadoReserva(String estado) {
        this.estado = estado;
    }
 
    public String getEstado() {
        return this.estado;
    }
}