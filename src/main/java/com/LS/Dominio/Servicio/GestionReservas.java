/*
 * EspacioService.java 1.0 30/03/2020
 */

/**
 * Esta clase contiene los servicios que gestionan las reservas
 *
 * @author Gonzalo Berné
 * @version 1.0, 17/04/2020
 */

package com.LS.Dominio.Servicio;

import com.LS.Dominio.Entidad.Reserva;
import com.LS.Dominio.Repositorio.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestionReservas {

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva crear(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

}


