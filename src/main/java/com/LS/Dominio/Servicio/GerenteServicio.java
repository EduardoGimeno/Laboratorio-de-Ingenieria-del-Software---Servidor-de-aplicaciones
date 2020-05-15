/*
 * ObtenerEspacios.java 1.0 09/05/2020
 */

/**
 * Esta clase contiene el servicio de autenticación de los gerentes
 *
 * @author Gonzalo Berné
 * @version 1.0, 09/05/2020
 */

package com.LS.Dominio.Servicio;

import com.LS.Dominio.Repositorio.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GerenteServicio {

    @Autowired
    private GerenteRepository gerenteRepository;


    public Boolean logIn(String nombre, String password) {
        return gerenteRepository.existsByNombreAndContrasena(nombre, password);
    }

}
