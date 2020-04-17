package com.LS.Dominio;

import com.LS.Dominio.Mensajeria.Receptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DominioApplication {

	@Autowired
	Receptor receptor;

	@Bean
	public void conexionBroker() throws Exception {
		//receptor.esperarMensajes();
	}

	public static void main(String[] args) {
		SpringApplication.run(DominioApplication.class, args);
	}

}
