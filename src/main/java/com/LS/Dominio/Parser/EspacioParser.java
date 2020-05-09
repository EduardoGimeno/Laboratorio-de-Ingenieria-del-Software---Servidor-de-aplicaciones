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
import org.springframework.stereotype.Service;

@Service
public class EspacioParser {

    public EspacioDTO entidadADTO (Espacio espacio) {
        EspacioDTO dto = new EspacioDTO();
        dto.setId(espacio.getId());
        dto.setTipo(espacio.getTipo());
        dto.setCapacidad(espacio.getCapacidad());
        dto.setUbicacion(espacio.getUbicacion());
        dto.setEquipamiento(espacio.getEquipamiento());
        dto.setNotas(espacio.getNotas());
        return dto;
    }

}
