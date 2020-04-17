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
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class Reserva {

    @Id
    private UUID id;

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
    private EstadoReserva estado;

    @NotNull
    @Embedded
    private Usuario usuario;

    @NotNull
    private UUID idEspacio;

    private final int diasLectivos = 5;

    public Reserva(Timestamp horaInicio, Timestamp horaFin, Timestamp fechaInicio, Timestamp fechaFin,
                   /*Boolean[] dias,*/ EstadoReserva estado, Usuario usuario, UUID idEspacio) {
       /* if (dias != null) {
            assert dias.length == diasLectivos;
        }*/
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        // this.dias = dias;
        this.estado = estado;
        this.usuario = usuario;
        this.idEspacio = idEspacio;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
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

    public EstadoReserva getEstado() {
        return this.estado;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public UUID getIdEspacio() {
        return this.idEspacio;
    }
}