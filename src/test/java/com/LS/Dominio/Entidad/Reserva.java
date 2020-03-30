/*
 * Reserva.java 1.0 30/03/2020
 */

/**
 * Esta clase reprensenta la entidad Reserva
 *
 * @author Gonzalo Berné
 * @version 1.0, 30/03/2020
 */

package com.LS.Dominio.Entidad;

import com.LS.Dominio.ObjetoValor.Usuario;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Reserva {

    @Id
    private String id;

    private Timestamp horaInicio;

    private Timestamp horaFin;

    private Timestamp fechaInicio;

    private Timestamp fechaFin;

    private String[] dias;

    private EstadoReserva estado;

    private Usuario  usuario;

    private String idEspacio;

    public Reserva(Timestamp horaInicio, Timestamp horaFin, Timestamp fechaInicio, Timestamp fechaFin,
                   String[] dias, EstadoReserva estado, Usuario usuario, String idEspacio) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.dias = dias;
        this.estado = estado;
        this.usuario = usuario;
        this.idEspacio = idEspacio;
    }
}