/*
 * ReservaRepository.java 1.0 30/03/2020
 */

/**
 * Esta interfaz es el repositorio de Reserva
 * utilizado para mantener persistencia de la entidad Reserva
 *
 * @author Gonzalo Berné
 * @version 1.0, 30/03/2020
 */

package com.LS.Dominio.Repositorio;

import com.LS.Dominio.Entidad.Reserva;
import org.springframework.data.repository.CrudRepository;

public interface ReservaRepository extends CrudRepository<Reserva, Integer> {

}
