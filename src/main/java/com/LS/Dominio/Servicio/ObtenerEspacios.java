/*
 * ObtenerEspacios.java 1.0 09/05/2020
 */

/**
 * Esta clase contiene los servicios que obtienen espacios
 *   a partir del identificador o del edificio y del tipo
 *
 * @author Gonzalo Berné
 * @version 1.0, 09/05/2020
 */

package com.LS.Dominio.Servicio;

import com.LS.Dominio.Entidad.Espacio;
import com.LS.Dominio.Repositorio.EspacioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObtenerEspacios {

    @Autowired
    private EspacioRepository espacioRepository;

    public Optional<Espacio> obtenerInformacion(String id) {
        return espacioRepository.findById(id);
    }

    public List<Espacio> obtenerPorEdificioYTipo(String edificio, String tipo) {
        return espacioRepository.findByEdificioYTipo(edificio, tipo);
    }
}
