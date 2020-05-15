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

    public Optional<Espacio> modificar(DatosDTO datos) {
        Optional<Espacio> espacioOptional = obtenerEspacios.obtenerInformacion(datos.getId());
        if (espacioOptional.isPresent()) {
            espacioOptional.get().modificar(datos.getCapacidad(), datos.getNotas());
            return Optional.of(espacioRepository.save(espacioOptional.get()));
        } else {
            return Optional.empty();
        }
    }

    public Boolean cambiarReservable(String id) {
        Boolean cambiado = false;
        Optional<Espacio> espacioOptional = espacioRepository.findById(id);
        if (espacioOptional.isPresent()) {
            cambiado = espacioOptional.get().cambiarReservable();
            espacioRepository.save(espacioOptional.get());
        }
        return cambiado;
    }
}
