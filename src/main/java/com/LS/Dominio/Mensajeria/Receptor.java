package com.LS.Dominio.Mensajeria;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class Receptor{

    private Logger log = LoggerFactory.getLogger(Receptor.class);

    private final static String COLA_ENTRADA = "entrada";
    private final static String COLA_SALIDA = "salida";
    private Channel canal;

    public Receptor() throws Exception {
        ConnectionFactory factoria = new ConnectionFactory();
        factoria.setHost("localhost");
        Connection conexion = factoria.newConnection();
        canal = conexion.createChannel();
        canal.queueDeclare(COLA_ENTRADA, false, false, false, null);
        canal.queueDeclare(COLA_SALIDA, false, false, false, null);
    }

    public void esperarMensajes() throws Exception {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String mensaje = new String(delivery.getBody(), StandardCharsets.UTF_8);
            log.info(" [x] Recibido: '"+ mensaje + "'");
        };
        canal.basicConsume(COLA_ENTRADA, true, deliverCallback, consumerTag -> { });
        log.info("Esperando mensajes...");
    }

    public void devolverMensajes(String mensaje) throws Exception {
        canal.basicPublish("", COLA_SALIDA, null, mensaje.getBytes());
        log.info(" [x] Enviado '" + mensaje + "'");
    }
}
