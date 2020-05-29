/*
 * ObjetosValorParser.java 1.0 29/05/2020
 */

/**
 * Esta clase contiene los servicios que parsean los ObjetosValor(OV) a DTOs y viceversa
 *
 * @author Gonzalo Berné
 * @version 1.0, 09/05/2020
 */

package com.LS.Dominio.Parser;

import DTO.EquipamientoDTO;
import DTO.UbicacionDTO;
import DTO.UsuarioDTO;
import com.LS.Dominio.ObjetoValor.Equipamiento;
import com.LS.Dominio.ObjetoValor.Ubicacion;
import com.LS.Dominio.ObjetoValor.Usuario;
import org.springframework.stereotype.Service;

@Service
public class ObjetosValorParser {


    public UbicacionDTO ubicacionOVADTO (Ubicacion ubicacion) {
        UbicacionDTO dto = new UbicacionDTO();
        dto.setEdificio(ubicacion.getEdificio());
        dto.setPlanta(ubicacion.getPlanta());
        return dto;
    }

    public EquipamientoDTO equipamientoOVADTO (Equipamiento equipamiento) {
        EquipamientoDTO dto = new EquipamientoDTO();
        dto.setTipo(equipamiento.getTipo());
        dto.setCantidad(equipamiento.getCantidad());
        dto.setMaxCantidad(equipamiento.getMaxCantidad());
        return dto;
    }

    public UsuarioDTO usuarioOVADTO (Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre(usuario.getNombre());
        dto.setApellidos(usuario.getApellidos());
        dto.setEmail(usuario.getEmail());
        dto.setNIA(usuario.getNIA());
        dto.setTelefono(usuario.getTelefono());
        return dto;
    }

    public Usuario usuarioDTOAOV (UsuarioDTO dto) {
        return new Usuario(dto.getNombre(), dto.getApellidos(), dto.getEmail(),
                dto.getNIA(), dto.getTelefono());
    }


}
