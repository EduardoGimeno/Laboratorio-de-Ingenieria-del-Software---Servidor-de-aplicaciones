/*
 * EspacioParser.java 1.0 09/05/2020
 */

/**
 * Esta clase contiene el servicio que parsea la entidad Espacio a EspacioDTO
 *
 * @author Gonzalo Berné
 * @version 1.0, 09/05/2020
 */

package com.LS.Dominio.Parser;

import DTO.EspacioDTO;
import com.LS.Dominio.Entidad.Espacio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class EspacioParser {

    @Autowired
    ObjetosValorParser objetosValorParser;

    public EspacioDTO entidadADTO (Espacio espacio) {
        EspacioDTO dto = new EspacioDTO();
        dto.setId(espacio.getId());
        dto.setTipo(espacio.getTipo());
        dto.setCapacidad(espacio.getCapacidad());
        dto.setUbicacion(objetosValorParser.ubicacionOVADTO(espacio.getUbicacion()));
        dto.setEquipamiento(espacio.getEquipamiento().stream()
                .map(objetosValorParser::equipamientoOVADTO)
                .collect(Collectors.toList()));
        dto.setNotas(espacio.getNotas());
        return dto;
    }

}
