package com.LS.Dominio.Parser;

import DTO.HorarioDTO;
import DTO.ReservaDTO;
import com.LS.Dominio.Entidad.Reserva;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ReservaParser {

    public ReservaDTO entidadADTO (Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setIdEspacio(reserva.getIdEspacio());
        dto.setUsuario(reserva.getUsuario());
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
                dto.getEstado(), dto.getUsuario(), dto.getIdEspacio());
    }

    public HorarioDTO ListaAHorarioDTO(List<Reserva> reservas) {
        HorarioDTO horario = new HorarioDTO();
        horario.setIdEspacio(reservas.get(0).getIdEspacio());
        horario.setHoraInicio(new Timestamp(0,0,0,8,0,0,0));
        horario.setHoraFin(new Timestamp(0,0,0,21,0,0,0));
        for (Reserva reserva: reservas) {
            Integer horas = reserva.getHoraInicio().getHours() - reserva.getHoraFin().getHours();
            for (int i = 0; i <= horas; i++) {
                horario.addHoraOcupada(new Timestamp(0,0,0,horas + i,
                        0,0,0));
            }
        }
        return horario;
    }
}
