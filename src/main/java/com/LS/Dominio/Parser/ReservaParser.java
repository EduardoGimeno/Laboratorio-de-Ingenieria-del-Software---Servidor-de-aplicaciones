/*
 * ReservaParser.java 1.0 ??/??/2020
 */

/**
 * Esta clase contiene los servicios que parsean la entidad Reserva
 *   a ReservaDTO y viceversa. Tambien a objeto HorarioDTO
 *
 * @author Gonzalo Berné
 * @version 1.0, ??/??/2020
 */

package com.LS.Dominio.Parser;

import DTO.HorarioDTO;
import DTO.ReservaDTO;
import Enum.Dia;
import java.util.Calendar;
import com.LS.Dominio.Entidad.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Service
public class ReservaParser {

    @Autowired
    ObjetosValorParser objetosValorParser;


    public ReservaDTO entidadADTO (Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setIdEspacio(reserva.getIdEspacio());
        dto.setUsuario(objetosValorParser.usuarioOVADTO(reserva.getUsuario()));
        dto.setDias(reserva.getDias());
        dto.setEstado(reserva.getEstado());
        dto.setFechaInicio(reserva.getFechaInicio());
        dto.setFechaFin(reserva.getFechaFin());
        dto.setHoraInicio(reserva.getHoraInicio());
        dto.setHoraFin(reserva.getHoraFin());
        return dto;
    }

    public Reserva DTOAEntidad (ReservaDTO dto) {
        if (dto.getDias().isEmpty()) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dto.getFechaInicio());
            dto.addDia(calendarDaytoDia(cal.get(Calendar.DAY_OF_WEEK)));
            cal.add(Calendar.DATE, 1);
            dto.setFechaFin(new Timestamp(cal.getTimeInMillis()));
        }
        System.out.println("NIA: " + dto.getUsuario().getNIA());
        return new Reserva(dto.getHoraInicio(), dto.getHoraFin(),
                dto.getFechaInicio(), dto.getFechaFin(), dto.getDiasString(),
                dto.getEstado(), objetosValorParser.usuarioDTOAOV(dto.getUsuario()), dto.getIdEspacio());
    }

    public HorarioDTO ListaReservasAHorarioDTO(String idEspacio, List<Reserva> reservas, Timestamp dia) {
        HorarioDTO horario = new HorarioDTO();
        horario.setIdEspacio(idEspacio);
        horario.setDia(dia);
        horario.setHoraInicio(8);
        horario.setHoraFin(22);
        if (reservas != null) {
            for (Reserva reserva : reservas) {
                Integer horas = reserva.getHoraFin() - reserva.getHoraInicio();
                for (int i = 0; i <= horas; i++) {
                    horario.addHoraOcupada(reserva.getHoraInicio() + i);
                }
            }
        }
        return horario;
    }

    public Dia calendarDaytoDia(Integer diaCalendario) {
        if (diaCalendario == 1) {
            return Dia.DOMINGO;
        } else if (diaCalendario == 2) {
            return Dia.LUNES;
        } else if (diaCalendario == 3) {
            return Dia.MARTES;
        } else if (diaCalendario == 4) {
            return Dia.MIERCOLES;
        } else if (diaCalendario == 5) {
            return Dia.JUEVES;
        } else if (diaCalendario == 6) {
            return Dia.VIERNES;
        } else {
            return Dia.SABADO;
        }
    }
}
