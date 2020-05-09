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

import com.LS.Dominio.Entidad.Espacio;
import com.LS.Dominio.Repositorio.EspacioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModificarEspacio {

    @Autowired
    private EspacioRepository espacioRepository;

    @Autowired
    private ObtenerEspacios obtenerEspacios;

    public Optional<Espacio> modificar(String id, Optional<Integer> capacidad, Optional<String> notas) {
        Optional<Espacio> espacioOptional = obtenerEspacios.obtenerInformacion(id);
        if (espacioOptional.isPresent()) {
            espacioOptional.get().modificar(capacidad, notas);
            return Optional.of(espacioRepository.save(espacioOptional.get()));
        } else {
            return Optional.empty();
        }
    }
}
