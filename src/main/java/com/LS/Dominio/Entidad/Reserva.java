/*
 * Reserva.java 1.0 30/03/2020
 */

/**
 * Esta clase reprensenta la entidad Reserva
 *
 * @author Gonzalo Berné
 * @author Eduardo Gimeno
 * @version 2.0, 01/04/2020
 */

package com.LS.Dominio.Entidad;

import ObjetoValor.Usuario;
import ObjetoValor.EstadoReserva;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class Reserva {

    @Id
    private String id;

    @NotNull
    private Timestamp horaInicio;

    @NotNull
    private Timestamp horaFin;

    @NotNull
    private Timestamp fechaInicio;

    @NotNull
    private Timestamp fechaFin;

    //private Boolean dias;

    @NotNull
    private String estado;

    @NotNull
    @Embedded
    private Usuario usuario;

    @NotNull
    private String idEspacio;

    //private final int diasLectivos = 5;

    public Reserva(Timestamp horaInicio, Timestamp horaFin, Timestamp fechaInicio, Timestamp fechaFin,
                   /*Boolean[] dias,*/ EstadoReserva estado, Usuario usuario, String idEspacio) {
       /* if (dias != null) {
            assert dias.length == diasLectivos;
        }*/
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        // this.dias = dias;
        this.estado = estado.getEstado();
        this.usuario = usuario;
        this.idEspacio = idEspacio;
        this.id = UUID.randomUUID().toString();
    }

    public Reserva() {}

    public String getId() {
        return this.id;
    }

    public Timestamp getHoraInicio() {
        return this.horaInicio;
    }

    public Timestamp getHoraFin() {
        return this.horaFin;
    }

    public Timestamp getFechaInicio() {
        return this.fechaInicio;
    }

    public Timestamp getFechaFin() {
        return this.fechaFin;
    }

   /* public Boolean[] getDias() {
        return this.dias;
    }*/

    public void setEstado(EstadoReserva estado) {
        this.estado = estado.getEstado();
    }

    public EstadoReserva getEstado() {
        if (this.estado.equals(EstadoReserva.ACEPTADA.getEstado())) {
            return EstadoReserva.ACEPTADA;
        } else if (this.estado.equals(EstadoReserva.PENDIENTE.getEstado())) {
            return EstadoReserva.PENDIENTE;
        }
        return EstadoReserva.RECHAZADA;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public String getIdEspacio() {
        return this.idEspacio;
    }
}