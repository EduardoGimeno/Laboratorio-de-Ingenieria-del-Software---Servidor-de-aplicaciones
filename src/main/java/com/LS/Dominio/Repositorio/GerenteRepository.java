/*
 * GerenteRepository.java 1.0 01/04/2020
 */

/**
 * Esta interfaz es el repositorio de Gerente
 * utilizado para mantener persistencia de la entidad Gerente
 *
 * @author Eduardo Gimeno
 * @version 1.0, 01/04/2020
 */

package com.LS.Dominio.Repositorio;

import com.LS.Dominio.Entidad.Gerente;
import org.springframework.data.repository.CrudRepository;

public interface GerenteRepository extends CrudRepository<Gerente, String> {

    Boolean existsBynombre(String nombre);
}