/*
 * Espacio.java 1.0 30/03/2020
 */

/**
 * Esta clase reprensenta la entidad Espacio
 *
 * @author Gonzalo Berné
 * @author Eduardo Gimeno
 * @version 2.0, 01/04/2020
 */

package com.LS.Dominio.Entidad;



import com.LS.Dominio.ObjetoValor.Equipamiento;
import com.LS.Dominio.ObjetoValor.Ubicacion;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Espacio {

    @Id
    private String id;

    @NotNull
    private String tipo;

    @NotNull
    @Min(0)
    private int capacidad;

    @NotNull
    @Embedded
    @ElementCollection(targetClass= Equipamiento.class, fetch=FetchType.EAGER)
    private List<Equipamiento> equipamiento = new ArrayList<>();

    @NotNull
    @Embedded
    private Ubicacion ubicacion;

    private String notas;

    @NotNull
    private Boolean reservable;

    /*public Espacio(String id, String tipo, int capacidad, // List<Equipamiento> equipamiento,
                   Ubicacion ubicacion) {
        this.tipo = tipo;
        this.capacidad = capacidad;
        // this.equipamiento = equipamiento;
        this.ubicacion = ubicacion;
        this.id = id;
    }*/

    public String getId() {
        return this.id;
    }

    public String getTipo() {
        return this.tipo;
    }

    public int getCapacidad() {
        return this.capacidad;
    }

    public List<Equipamiento> getEquipamiento() {
        return this.equipamiento;
    }

    public Ubicacion getUbicacion() {
        return this.ubicacion;
    }

    public String getNotas() {
        return this.notas;
    }

    public Boolean getReservable() { return this.reservable; }

    public void modificar (List<Equipamiento> equipamiento, int capacidad, Boolean reservable, String notas) {
        this.equipamiento = equipamiento;
        this.capacidad = capacidad;
        this.reservable = reservable;
        this.notas = notas;
    }
}