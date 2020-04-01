/*
 * EspacioService.java 1.0 30/03/2020
 */

/**
 * Esta clase contiene los servicios que afectan
 * a las entidades Espacios
 *
 * @author Gonzalo Berné
 * @version 1.0, 30/03/2020
 */

package com.LS.Dominio.Servicio;

import com.LS.Dominio.Entidad.Espacio;
import com.LS.Dominio.Repositorio.EspacioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EspacioService {

    @Autowired
    private EspacioRepository espacioRepository;

    public Optional<Espacio> obtenerInformacion(String id) {
        return espacioRepository.findById(id);
    }

}
