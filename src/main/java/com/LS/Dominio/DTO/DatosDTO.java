package com.LS.Dominio.DTO;

import java.util.UUID;

public class DatosDTO {

    private UUID id;
    private boolean pizarra;
    private boolean proyector;
    private int capacidad;
    private String notas;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isPizarra() {
        return pizarra;
    }

    public void setPizarra(boolean pizarra) {
        this.pizarra = pizarra;
    }

    public boolean isProyector() {
        return proyector;
    }

    public void setProyector(boolean proyector) {
        this.proyector = proyector;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
