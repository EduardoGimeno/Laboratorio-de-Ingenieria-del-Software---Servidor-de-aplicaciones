package com.LS.Dominio.Servicio;

import com.LS.Dominio.Entidad.Espacio;
import com.LS.Dominio.Entidad.Reserva;
import com.LS.Dominio.Repositorio.EspacioRepository;
import com.LS.Dominio.Repositorio.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class FiltrarBusquedaReservas {

    @Autowired
    private EspacioRepository espacioRepository;

    @Autowired
    private ObtenerReservas obtenerReservas;

    public Collection<Reserva> filtrar (String edificio, String tipo, Timestamp fechaIni,
                                        Timestamp fechaFin, int horaIni, int horaFin) {
        List<Reserva> reservasFitradas = new ArrayList<>();
        List<Espacio> espaciosFiltrados = espacioRepository.findByUbicacionEdificioAndTipo(edificio, tipo);
        for (Espacio espacio: espaciosFiltrados) {
            reservasFitradas.addAll(obtenerReservas.obtenerPorEspacioFechasYHoras(espacio.getId(), fechaIni,
                    fechaFin, horaIni, horaFin));
        }
        return reservasFitradas;
    }

}
