package com.LS.Dominio.Mensajeria;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Receptor{

    private final static String COLA_ENTRADA = "entrada";
    private final static String COLA_SALIDA = "salida";
    private Connection conexion;
    private Channel canal;

    public Receptor() throws Exception {
        ConnectionFactory factoria = new ConnectionFactory();
        factoria.setHost("localhost");
        conexion = factoria.newConnection();
        canal = conexion.createChannel();
        canal.queueDeclare(COLA_ENTRADA, false, false, false, null);
        canal.queueDeclare(COLA_SALIDA, false, false, false, null);
    }

    public void esperarMensajes() throws Exception {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String mensaje = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Recibido: '"+ mensaje + "'");
        };
        canal.basicConsume(COLA_ENTRADA, true, deliverCallback, consumerTag -> { });
        System.out.println("Esperando mensajes...");
    }

    public void devolverMensajes(String mensaje) throws Exception {
        canal.basicPublish("", COLA_ENTRADA, null, mensaje.getBytes());
        System.out.println(" [x] Enviado '" + mensaje + "'");
    }
}
