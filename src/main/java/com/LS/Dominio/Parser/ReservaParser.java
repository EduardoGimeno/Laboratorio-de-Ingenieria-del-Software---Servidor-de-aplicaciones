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
import com.LS.Dominio.Entidad.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
        return new Reserva(dto.getHoraInicio(), dto.getHoraFin(),
                dto.getFechaInicio(), dto.getFechaFin(), dto.getDiasString(),
                dto.getEstado(), objetosValorParser.usuarioDTOAOV(dto.getUsuario()), dto.getIdEspacio());
    }

    public HorarioDTO ListaReservasAHorarioDTO(String idEspacio, List<Reserva> reservas, Timestamp dia) {
        HorarioDTO horario = new HorarioDTO();
        horario.setIdEspacio(idEspacio);
        horario.setDia(dia);
        horario.setHoraInicio(8);
        horario.setHoraFin(21);
        if (reservas != null) {
            for (Reserva reserva : reservas) {
                Integer horas = reserva.getHoraFin().getHours() - reserva.getHoraInicio().getHours();
                for (int i = 0; i <= horas; i++) {
                    horario.addHoraOcupada(reserva.getHoraInicio().getHours() + i);
                }
            }
        }
        return horario;
    }
}
