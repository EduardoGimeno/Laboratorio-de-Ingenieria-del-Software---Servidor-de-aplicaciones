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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.Collection;

public interface ReservaRepository extends CrudRepository<Reserva, String> {

    Collection<Reserva> findByIdEspacio(String idEspacio);

    @Query(value = "SELECT r FROM Reserva r WHERE r.idEspacio = ?1 " +
            "AND r.horaInicio > ?2 AND r.horaInicio < ?3")
    Collection<Reserva> findByIdEspacioFecha(String idEspacio, Timestamp dia, Timestamp diaSiguiente);
}
