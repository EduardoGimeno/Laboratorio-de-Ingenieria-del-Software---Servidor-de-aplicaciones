package com.LS.Dominio.Repositorio;

import com.LS.Dominio.Entidad.Gerente;
import org.springframework.data.repository.CrudRepository;

public interface GerenteRepository extends CrudRepository<Gerente, String> {

    Boolean existsBynombre(String nombre);
}