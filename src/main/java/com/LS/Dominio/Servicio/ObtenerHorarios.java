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

import Enum.Dia;
import DTO.HorarioDTO;
import com.LS.Dominio.Parser.ReservaParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ObtenerHorarios {

    @Autowired
    private ReservaParser reservaParser;

    @Autowired
    private ObtenerReservas obtenerReservas;

    public List<HorarioDTO> obtenerPorEspacioEntreFechas (String idEspacio, Timestamp fechaInicio, Timestamp fechaFin) {
        List<HorarioDTO> horarios = new ArrayList<>();
        int dias = fechaFin.getDate() - fechaInicio.getDate();
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

    public List<HorarioDTO> obtenerPorEspaciosEntreFechasYDiasConcretos (String idEspacio, Timestamp fechaInicio,
                                                                         Timestamp fechaFin, List<Dia> dias) {
        List<HorarioDTO> horarios = new ArrayList<>();
        int numeroDias = fechaFin.getDate() - fechaInicio.getDate();
        Timestamp dia = new Timestamp(fechaInicio.getYear(), fechaInicio.getMonth(),
                fechaInicio.getDate(), 0, 0, 0, 0);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dia);
        for (int i = 0; i <= numeroDias; i++) {
            for (Dia diaSemana: dias) {
                if (diaSemana.getDia() == cal.get(Calendar.DAY_OF_WEEK)) {
                    horarios.add(obtenerPorEspacioYDia(idEspacio, new Timestamp(cal.getTime().getTime())));
                    cal.add(Calendar.DATE, 1);
                }
            }
        }
        return horarios;
    }

    public HorarioDTO obtenerPorEspacioYDia (String idEspacio, Timestamp dia) {
        return reservaParser.ListaReservasAHorarioDTO(idEspacio, obtenerReservas
                .obtenerPorEspacioFechaYDia(idEspacio, dia), dia);
    }

}


