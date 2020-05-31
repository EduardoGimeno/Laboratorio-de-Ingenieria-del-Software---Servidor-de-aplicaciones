package com.LS.Dominio;

import DTO.DatosDTO;
import DTO.EquipamientoDTO;
import com.LS.Dominio.Servicio.ModificarEspacio;
import Enum.TipoEquipamiento;

import com.LS.Dominio.Mensajeria.Receptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DominioApplication {

	@Autowired
	Receptor receptor;

	@Autowired
	ModificarEspacio modificarEspacio;

	@Bean
	public void conexionBroker() throws Exception {
		receptor.esperarMensajes();
	}

	@Bean
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
	}

	public static void main(String[] args) {
		SpringApplication.run(DominioApplication.class, args);
	}

}
