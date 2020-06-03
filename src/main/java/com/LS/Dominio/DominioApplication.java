package com.LS.Dominio;

import DTO.BusquedaDTO;
import DTO.DatosDTO;
import DTO.EquipamientoDTO;
import DTO.HorarioDTO;
import com.LS.Dominio.Entidad.Espacio;
import com.LS.Dominio.Entidad.Reserva;
import com.LS.Dominio.ObjetoValor.Usuario;
import com.LS.Dominio.Servicio.FiltrarBusquedaEspacios;
import com.LS.Dominio.Servicio.GestionReservas;
import com.LS.Dominio.Servicio.ModificarEspacio;
import Enum.*;

import com.LS.Dominio.Mensajeria.Receptor;
import com.LS.Dominio.Servicio.ObtenerHorarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class DominioApplication {

	@Autowired
	Receptor receptor;

	@Autowired
	ModificarEspacio modificarEspacio;

	@Autowired
	GestionReservas gestionReservas;

	@Autowired
	FiltrarBusquedaEspacios filtrarBusquedaEspacios;

	@Autowired
	ObtenerHorarios obtenerHorarios;

	@Bean
	public void conexionBroker() throws Exception {
		receptor.esperarMensajes();
	}


	/*@Bean
	public void modificarEspacioBean() {
		DatosDTO datos = new DatosDTO();
		datos.setId("id_espacio");
		datos.setCapacidad(500);
		datos.setReservable(false);
		datos.setNotas("Modificado 1.1");

		List<EquipamientoDTO> equipamientos = new ArrayList<>();
		EquipamientoDTO e1 = new EquipamientoDTO();
		e1.setTipo(TipoEquipamiento.PIZARRA); // 5
		e1.setCantidad(2);
		e1.setMaxCantidad(5);
		EquipamientoDTO e2 = new EquipamientoDTO();
		e2.setTipo(TipoEquipamiento.CANON);	// 0
		e2.setCantidad(1);
		e2.setMaxCantidad(1);
		EquipamientoDTO e3 = new EquipamientoDTO();
		e3.setTipo(TipoEquipamiento.ORDENADOR); // 6
		e3.setCantidad(20);
		e3.setMaxCantidad(25);
		equipamientos.add(e1);
		equipamientos.add(e2);
		equipamientos.add(e3);
		datos.setEquipamiento(equipamientos);

		modificarEspacio.modificar(datos);
		System.out.println("MODIFICADO");
	}*/

	/*@Bean
	public void crearReservas() {
		Timestamp fechaInicio = new Timestamp(120, 5,1, 0, 0, 0, 0);
		Timestamp fechaFin = new Timestamp(120, 5,13, 59, 59, 0, 0);
		Usuario usuario = new Usuario("Gonzalo", "Berné", "715891@unizar.es", 715891, 100000000);

		Timestamp horaIniR1 = new Timestamp(0, 0, 0, 8, 0, 0, 0);
		Timestamp horaFinR1 = new Timestamp(0, 0, 0, 10, 0, 0, 0);
		List<Integer> diasR1 = new ArrayList<>();
		diasR1.add(4);
		Reserva r1 = new Reserva(horaIniR1, horaFinR1, fechaInicio, fechaFin, diasR1, EstadoReserva.PENDIENTE,
				usuario, "id_espacio");
		//gestionReservas.crear(r1);

		Timestamp horaIniR2 = new Timestamp(0, 0, 0, 16, 0, 0, 0);
		Timestamp horaFinR2 = new Timestamp(0, 0, 0, 17, 0, 0, 0);
		List<Integer> diasR2 = new ArrayList<>();
		diasR2.add(4);
		Reserva r2 = new Reserva(horaIniR2, horaFinR2, fechaInicio, fechaFin, diasR2, EstadoReserva.PENDIENTE,
				usuario, "id_espacio");
		//gestionReservas.crear(r2);

		Timestamp horaIniR3 = new Timestamp(0, 0, 0, 8, 0, 0, 0);
		Timestamp horaFinR3 = new Timestamp(0, 0, 0, 10, 0, 0, 0);
		List<Integer> diasR3 = new ArrayList<>();
		diasR3.add(4);
		Reserva r3 = new Reserva(horaIniR3, horaFinR3, fechaInicio, fechaFin, diasR3, EstadoReserva.PENDIENTE,
				usuario, "id_espacio3");
		gestionReservas.crear(r3);

		Timestamp horaIniR4 = new Timestamp(0, 0, 0, 16, 0, 0, 0);
		Timestamp horaFinR4 = new Timestamp(0, 0, 0, 17, 0, 0, 0);
		List<Integer> diasR4 = new ArrayList<>();
		diasR4.add(Dia.LUNES.getDia());
		Reserva r4 = new Reserva(horaIniR4, horaFinR4, fechaInicio, fechaFin, diasR4, EstadoReserva.PENDIENTE,
				usuario, "id_espacio3");
		//gestionReservas.crear(r4);
	}*/

	/*@Bean
	public void busqueda() {
		Timestamp fechaInicio = new Timestamp(120, 5,1, 0, 0, 0, 0);
		Timestamp fechaFin = new Timestamp(120, 5,14, 0, 0, 0, 0);
		List<Dia> dias = new ArrayList<>();
		dias.add(Dia.LUNES);
        dias.add(Dia.MARTES);
        dias.add(Dia.MIERCOLES);
        dias.add(Dia.JUEVES);
        dias.add(Dia.VIERNES);
        dias.add(Dia.SABADO);
        dias.add(Dia.DOMINGO);

		BusquedaDTO busqueda = new BusquedaDTO();
		busqueda.setEdificio("Ada");
		busqueda.setTipoEspacio("Aula");
		busqueda.setEquipamiento(new ArrayList<>());
		busqueda.setCapacidad(0);
		busqueda.setFechaInicio(fechaInicio);
		busqueda.setFechaFin(fechaFin);
		busqueda.setHoraInicio(2);
		busqueda.setHoraFin(21);
		busqueda.setDias(dias);
		busqueda.setPeriodo(true);
		Collection<Espacio> espacios = filtrarBusquedaEspacios.filtrar(busqueda);
		System.out.println("Espacios devueltos");
		for (Espacio e: espacios) {
			System.out.println(e.getId());
		}
		Timestamp fecha = new Timestamp(120, 5,5, 0, 0, 0, 0);
		//HorarioDTO horario = obtenerHorarios.obtenerPorEspacioYDia("id_espacio3", fecha);
		List<HorarioDTO> horarios = obtenerHorarios.obtenerPorEspaciosEntreFechasYDiasConcretos("id_espacio3", fechaInicio, fechaFin, dias);
		for (HorarioDTO horario: horarios) {
			System.out.println("HORAS: " + horario.getDia());
			for (Integer i : horario.getHorasOcupadas()) {
				System.out.println(i);
			}
		}
	}*/

	public static void main(String[] args) {
		SpringApplication.run(DominioApplication.class, args);
	}

}
