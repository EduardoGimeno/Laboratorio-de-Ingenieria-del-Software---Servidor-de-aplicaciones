/*
 * EspacioService.java 1.0 30/03/2020
 */

/**
 * Esta clase contiene los servicios que gestionan las reservas
 *
 * @author Gonzalo Berné
 * @version 2.0, 22/04/2020
 */

package com.LS.Dominio.Servicio;

import Enum.EstadoReserva;
import com.LS.Dominio.Entidad.Reserva;
import com.LS.Dominio.Parser.ReservaParser;
import com.LS.Dominio.Repositorio.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Optional;

@Service
public class GestionReservas {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private EnviarEmail enviarEmail;

    public Reserva crear(Reserva reserva) {
        Reserva reservaGuardada = reservaRepository.save(reserva);
        enviarEmail.enviarReservaCreada(reservaGuardada);
        return reservaGuardada;
    }

    public Optional<Reserva> cambiarEstado(String id, EstadoReserva estado, String motivo) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);
        reservaOptional.ifPresent(reserva -> {
            reserva.setEstado(estado);
            reservaRepository.save(reserva);
            enviarEmail.cambioEstadoReserva(reserva, estado, motivo);
        });
        return reservaOptional;
    }

}


