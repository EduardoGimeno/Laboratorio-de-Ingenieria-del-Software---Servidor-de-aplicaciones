/*
 * EspacioRepository.java 1.0 30/03/2020
 */

/**
 * Esta interfaz es el repositorio de Espacio
 * utilizado para mantener persistencia de la entidad Espacio
 *
 * @author Gonzalo Berné
 * @version 1.0, 30/03/2020
 */

package com.LS.Dominio.Repositorio;

import com.LS.Dominio.Entidad.Espacio;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface EspacioRepository extends CrudRepository<Espacio, Integer> {

    Optional<Espacio> findById (String id);
}
