package com.LS.Dominio.DTO;

import java.io.Serializable;

public class BusquedaDTO implements Serializable {
    private String edificio;
    private String tipoEspacio;
    private boolean pizarra;
    private boolean proyector;
    private int capacidad;

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public String getTipoEspacio() {
        return tipoEspacio;
    }

    public void setTipoEspacio(String tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
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

}
