package com.LS.Dominio.Mensajeria;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class Receptor{

    private Logger log = LoggerFactory.getLogger(Receptor.class);

    private final static String COLA_ENTRADA = "entrada";
    private final static String COLA_SALIDA = "salida";
    private final static String ENV_AMQPURL_NAME = "CLOUDAMQP_URL";
    private Channel canal;

    public Receptor() throws Exception {
        ConnectionFactory factoria = new ConnectionFactory();
        String amqpURL = System.getenv(ENV_AMQPURL_NAME) != null ?
                System.getenv().get(ENV_AMQPURL_NAME) : "amqp://localhost";
        try {
            factoria.setUri(amqpURL);
        }  catch (Exception e) {
            System.out.println(" [*] AQMP broker no encontrado en " + amqpURL);
            System.exit(-1);
        }
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
