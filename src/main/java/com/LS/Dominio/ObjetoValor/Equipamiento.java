/*
 * Equipamiento.java 1.0 30/03/2020
 */

/**
 * Esta clase reprensenta el objeto valor Equipamiento
 *
 * @author Gonzalo Berné
 * @author Eduardo Gimeno
 * @version 2.0, 01/04/2020
 */

package com.LS.Dominio.ObjetoValor;

import javax.persistence.Embeddable;

@Embeddable
public class Equipamiento {

    private TipoEquipamiento tipo;

    private int cantidad;

    private int maxCantidad;

    public Equipamiento() {
    }

    public Equipamiento(TipoEquipamiento tipo, int cantidad, int maxCantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.maxCantidad = maxCantidad;
    }

    public String getTipo() {
        return tipo.getNombre();
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getMaxCantidad() {
        return maxCantidad;
    }

    public boolean anadir(int cantidad) {
        if (cantidad + this.cantidad <= this.maxCantidad) {
            this.cantidad += cantidad;
            return true;
        } else {
            return false;
        }
    }

    public boolean quitar(int cantidad) {
        if (this.cantidad - cantidad >= 0) {
            this.cantidad -= cantidad;
            return true;
        } else {
            return false;
        }
    }
}
