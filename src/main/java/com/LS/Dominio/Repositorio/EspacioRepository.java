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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface EspacioRepository extends CrudRepository<Espacio, String> {

    Optional<Espacio> findById (String id);

    List<Espacio> findByUbicacionEdificioAndTipo (String edificio, String tipo);

    @Query(value = "SELECT e FROM Espacio e WHERE e.ubicacion.edificio = ?1 AND " +
            "e.tipo = ?2 AND e.capacidad > ?3 AND e.reservable = true")
    List<Espacio> findByUbicacionEdificioAndTipoAndCapacidadMinima (String edificio, String tipo, int capacidad);
}
