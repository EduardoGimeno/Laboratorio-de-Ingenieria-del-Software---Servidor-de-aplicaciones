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

import Enum.Dia;
import Enum.EstadoReserva;
import com.LS.Dominio.ObjetoValor.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
public class Reserva {

    @Id
    private String id;

    @NotNull
    private int horaInicio;

    @NotNull
    private int horaFin;

    @NotNull
    private Timestamp fechaInicio;

    @NotNull
    private Timestamp fechaFin;

    @NotNull
    @ElementCollection(targetClass=Integer.class, fetch=FetchType.EAGER)
    private List<Integer> dias = new ArrayList<>();

    @NotNull
    private String estado;

    @NotNull
    @Embedded
    private Usuario usuario;

    @NotNull
    private String idEspacio;


    public Reserva(int horaInicio, int horaFin, Timestamp fechaInicio, Timestamp fechaFin,
                   List<Integer> dias, EstadoReserva estado, Usuario usuario, String idEspacio) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.dias = dias;
        this.estado = estado.getEstado();
        this.usuario = usuario;
        this.idEspacio = idEspacio;
        this.id = UUID.randomUUID().toString();
    }

    public Reserva() {}

    public String getId() {
        return this.id;
    }

    public int getHoraInicio() {
        return this.horaInicio;
    }

    public int getHoraFin() {
        return this.horaFin;
    }

    public Timestamp getFechaInicio() {
        return this.fechaInicio;
    }

    public Timestamp getFechaFin() {
        return this.fechaFin;
    }

    public List<Dia> getDias() {
        return this.dias.stream()
                .map(this::getDia)
                .collect(Collectors.toList());
    }

    public Dia getDia(Integer dia) {
        if (dia.equals(Dia.LUNES.getDia())) {
            return Dia.LUNES;
        } else if (dia.equals(Dia.MARTES.getDia())) {
            return Dia.MARTES;
        } else if (dia.equals(Dia.MIERCOLES.getDia())) {
            return Dia.MIERCOLES;
        } else if (dia.equals(Dia.JUEVES.getDia())) {
            return Dia.JUEVES;
        } else if (dia.equals(Dia.VIERNES.getDia())) {
            return Dia.VIERNES;
        } else if (dia.equals(Dia.SABADO.getDia())) {
            return Dia.SABADO;
        } else {
            return Dia.DOMINGO;
        }
    }

    public static Dia getDiaGlobal(Integer dia) {
        if (dia.equals(Dia.LUNES.getDia())) {
            return Dia.LUNES;
        } else if (dia.equals(Dia.MARTES.getDia())) {
            return Dia.MARTES;
        } else if (dia.equals(Dia.MIERCOLES.getDia())) {
            return Dia.MIERCOLES;
        } else if (dia.equals(Dia.JUEVES.getDia())) {
            return Dia.JUEVES;
        } else if (dia.equals(Dia.VIERNES.getDia())) {
            return Dia.VIERNES;
        } else if (dia.equals(Dia.SABADO.getDia())) {
            return Dia.SABADO;
        } else {
            return Dia.DOMINGO;
        }
    }

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