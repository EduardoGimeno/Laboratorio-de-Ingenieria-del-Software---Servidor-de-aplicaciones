package com.LS.Dominio.DTO;

import com.LS.Aplicacion.ObjetoValor.Ubicacion;

import java.io.Serializable;
import java.util.UUID;

public class EspacioDTO implements Serializable {

    private UUID id;
    private String tipo;
    private int capacidad;
    private Ubicacion ubicacion;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
}
