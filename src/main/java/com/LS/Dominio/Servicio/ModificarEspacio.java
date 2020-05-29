/*
 * ObtenerEspacios.java 1.0 09/05/2020
 */

/**
 * Esta clase contiene el servicio para modificar los datos
 *   de un espacio
 *
 * @author Gonzalo Berné
 * @version 1.0, 09/05/2020
 */

package com.LS.Dominio.Servicio;

import DTO.DatosDTO;
import com.LS.Dominio.Entidad.Espacio;
import com.LS.Dominio.ObjetoValor.Equipamiento;
import com.LS.Dominio.Parser.ObjetosValorParser;
import com.LS.Dominio.Repositorio.EspacioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModificarEspacio {

    @Autowired
    private EspacioRepository espacioRepository;

    @Autowired
    private ObtenerEspacios obtenerEspacios;

    @Autowired
    ObjetosValorParser objetosValorParser;

    public Optional<Espacio> modificar(DatosDTO datos) {
        Optional<Espacio> espacioOptional = obtenerEspacios.obtenerInformacion(datos.getId());
        if (espacioOptional.isPresent()) {
            List<Equipamiento> equipamiento = datos.getEquipamiento().stream()
                    .map(objetosValorParser::equipamientoDTOAOV)
                    .collect(Collectors.toList());
            espacioOptional.get().modificar(equipamiento, datos.getCapacidad(), datos.isReservable(),
                    datos.getNotas());
            return Optional.of(espacioRepository.save(espacioOptional.get()));
        } else {
            return Optional.empty();
        }
    }

}
