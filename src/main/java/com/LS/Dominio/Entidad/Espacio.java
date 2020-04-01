/*
 * Espacio.java 1.0 30/03/2020
 */

/**
 * Esta clase reprensenta la entidad Espacio
 *
 * @author Gonzalo Berné
 * @version 1.0, 30/03/2020
 */

package com.LS.Dominio.Entidad;

import com.LS.Dominio.ObjetoValor.Equipamiento;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Espacio {

    @Id
    private String id;

    private String tipo;

    private int capacidad;

    private int piso;

    private String edificio;

    //private List<Equipamiento> equipamiento;
}