package com.LS.Dominio.Servicio;

import DTO.BusquedaDTO;
import com.LS.Dominio.Entidad.Espacio;
import com.LS.Dominio.Repositorio.EspacioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FiltrarBusquedaEspacios {

    @Autowired
    private EspacioRepository espacioRepository;

    public Collection<Espacio> filtrar (BusquedaDTO busquedaDTO) {
        if (busquedaDTO.getEdificio().equals("null")) {
            if (busquedaDTO.getTipoEspacio().equals("null")) {
                if (busquedaDTO.getCapacidad() < 0) {
                    return espacioRepository.obtenerTodos();
                } else {
                    return espacioRepository.findByCapacidadMinima(busquedaDTO.getCapacidad());
                }
            } else {
                if (busquedaDTO.getCapacidad() < 0) {
                    return espacioRepository.findByTipo(busquedaDTO.getTipoEspacio());
                } else {
                    return espacioRepository.findByTipoAndCapacidadMinima(
                            busquedaDTO.getTipoEspacio(), busquedaDTO.getCapacidad());
                }
            }
        } else {
            if (busquedaDTO.getTipoEspacio().equals("null")) {
                if (busquedaDTO.getCapacidad() < 0) {
                    return espacioRepository.findByUbicacionEdificio(busquedaDTO.getEdificio());
                } else {
                    return espacioRepository.findByUbicacionEdificioAndCapacidadMinima(
                            busquedaDTO.getEdificio(), busquedaDTO.getCapacidad());
                }
            } else {
                if (busquedaDTO.getCapacidad() < 0) {
                    return espacioRepository.findByUbicacionEdificioAndTipo(
                            busquedaDTO.getEdificio(), busquedaDTO.getTipoEspacio());
                } else {
                    return espacioRepository.findByUbicacionEdificioAndTipoAndCapacidadMinima(
                            busquedaDTO.getEdificio(), busquedaDTO.getTipoEspacio(), busquedaDTO.getCapacidad());
                }
            }
        }
    }

}
