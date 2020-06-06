/*
 * EspacioService.java 1.0 30/03/2020
 */

/**
 * Esta clase contiene los servicios que obtienen las reservas
 *   por espacio
 *
 * @author Gonzalo Berné
 * @version 1.0, 22/04/2020
 */

package com.LS.Dominio.Servicio;

import Enum.EstadoReserva;
import com.LS.Dominio.Entidad.Reserva;
import com.LS.Dominio.Repositorio.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Service
public class ObtenerReservas {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> obtenerReservasEspacio(String idEspacio) {
        return reservaRepository.findByIdEspacio(idEspacio);
    }

    public List<Reserva> obtenerReservasEstado(EstadoReserva estadoReserva) {
        return reservaRepository.findByEstado(estadoReserva.getEstado());
    }

    public List<Reserva> obtenerReservasEspacioEstado(String idEspacio, EstadoReserva estadoReserva) {
        return reservaRepository.findByIdEspacioAndEstado(idEspacio, estadoReserva.getEstado());
    }

    //??
    public List<Reserva> obtenerPorEspacioYFecha(String idEspacio, Timestamp fecha) {
        Timestamp dia = new Timestamp(fecha.getYear(), fecha.getMonth(),
                fecha.getDate(), 0, 0, 0, 0);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dia);
        cal.add(Calendar.DATE, 1);
        Timestamp diaSiguiente = new Timestamp(cal.getTime().getTime());
        return reservaRepository.findByIdEspacioYEntreDosFechas(idEspacio, dia, diaSiguiente);
    }

    public List<Reserva> obtenerPorEspacioFechaYDia(String idEspacio, Timestamp fecha) {
        Timestamp dia = new Timestamp(fecha.getYear(), fecha.getMonth(),
                fecha.getDate(), 0, 0, 0, 0);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dia);
        return reservaRepository.findByIdEspacioYDia(idEspacio, dia, cal.get(Calendar.DAY_OF_WEEK),
                EstadoReserva.ACEPTADA.getEstado());
    }

    public List<Reserva> obtenerPorEspacioFechasYHoras(String idEspacio, Timestamp fechaIni,
                                                  Timestamp fechaFin, int horaIni, int horaFin) {
        return reservaRepository.findByEspacioFechasYHoras(idEspacio, EstadoReserva.PENDIENTE.getEstado(),
                fechaIni, fechaFin, horaIni, horaFin);
    }

}


