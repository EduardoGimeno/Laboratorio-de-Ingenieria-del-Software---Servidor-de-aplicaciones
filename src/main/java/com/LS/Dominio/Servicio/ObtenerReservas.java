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
import java.util.Optional;

@Service
public class ObtenerReservas {

    @Autowired
    private ReservaRepository reservaRepository;

    public Optional<Reserva> obtenerReservaPorId(String id) {
        return reservaRepository.findById(id);
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
                                                       Timestamp fechaFin, int horaIni, int horaFin,
                                                       EstadoReserva estado) {
        return reservaRepository.findByEspacioFechasYHoras(idEspacio, estado.getEstado(),
                fechaIni, fechaFin, horaIni, horaFin);
    }

}


