package com.LS.Dominio.Parser;

import DTO.ReservaDTO;
import com.LS.Dominio.Entidad.Reserva;
import org.springframework.stereotype.Service;

@Service
public class ReservaParser {

    public ReservaDTO entidadADTO (Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setIdEspacio(reserva.getIdEspacio());
        dto.setUsuario(reserva.getUsuario());
        dto.setEstado(reserva.getEstado());
        dto.setFechaInicio(reserva.getFechaInicio());
        dto.setFechaFin(reserva.getFechaFin());
        dto.setHoraInicio(reserva.getHoraInicio());
        dto.setHoraFin(reserva.getHoraFin());
        return dto;
    }

    public Reserva DTOAEntidad (ReservaDTO dto) {
        return new Reserva(dto.getHoraInicio(), dto.getHoraFin(),
                dto.getFechaInicio(), dto.getFechaFin(), dto.getEstado(),
                dto.getUsuario(), dto.getIdEspacio());
    }
}
