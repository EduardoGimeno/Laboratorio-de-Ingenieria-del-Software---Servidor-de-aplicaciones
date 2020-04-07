package com.LS.Dominio.DTO;

import com.LS.Dominio.ObjetoValor.EstadoReserva;
import com.LS.Dominio.ObjetoValor.Usuario;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

public class ReservaDTO implements Serializable {

    private UUID id;
    private Timestamp horaInicio;
    private Timestamp horaFin;
    private Timestamp fechaInicio;
    private Timestamp fechaFin;
    private EstadoReserva estado;
    private Usuario usuario;
    private UUID idEspacio;
    private int diasLectivos = 5;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Timestamp getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Timestamp horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Timestamp getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Timestamp horaFin) {
        this.horaFin = horaFin;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Timestamp getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UUID getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(UUID idEspacio) {
        this.idEspacio = idEspacio;
    }

    public int getDiasLectivos() {
        return diasLectivos;
    }

    public void setDiasLectivos(int diasLectivos) {
        this.diasLectivos = diasLectivos;
    }
}
