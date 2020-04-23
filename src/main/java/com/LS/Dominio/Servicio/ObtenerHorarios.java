/*
 * EspacioService.java 1.0 30/03/2020
 */

/**
 * Esta clase contiene los servicios que obtienen los horarios
 *   de un espacio
 *
 * @author Gonzalo Berné
 * @version 1.0, 22/04/2020
 */

package com.LS.Dominio.Servicio;

import DTO.HorarioDTO;
import ObjetoValor.Dia;
import com.LS.Dominio.Entidad.Reserva;
import com.LS.Dominio.Parser.ReservaParser;
import com.LS.Dominio.Repositorio.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Service
public class ObtenerHorarios {

    @Autowired
    ReservaParser reservaParser;

    @Autowired
    private ObtenerReservas obtenerReservas;

    // PROBAR MAÑANA
    public List<HorarioDTO> obtenerPorEspacioEntreFechas (String idEspacio, Timestamp fechaInicio, Timestamp fechaFin) {
        List<HorarioDTO> horarios = new ArrayList<>();
        int dias = fechaInicio.getDate() - fechaFin.getDate();
        Timestamp dia = new Timestamp(fechaInicio.getYear(), fechaInicio.getMonth(),
                fechaInicio.getDate(), 0, 0, 0, 0);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dia);
        for (int i = 0; i <= dias; i++) {
            horarios.add(obtenerPorEspacioYDia(idEspacio, new Timestamp(cal.getTime().getTime())));
            cal.add(Calendar.DATE, 1);
        }
        return horarios;
    }

    public HorarioDTO obtenerPorEspacioYDia (String idEspacio, Timestamp dia) {
        return reservaParser.ListaAHorarioDTO(obtenerReservas
                .obtenerPorEspacioFechaYDia(idEspacio, dia));
    }

}


