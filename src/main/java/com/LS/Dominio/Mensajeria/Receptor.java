package com.LS.Dominio.Mensajeria;

import DTO.*;
import Enum.EstadoReserva;
import com.LS.Dominio.Entidad.*;
import com.LS.Dominio.Parser.*;
import com.LS.Dominio.Servicio.*;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.rabbitmq.client.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Receptor{

    @Autowired
    GestionReservas gestionReservas;

    @Autowired
    ObtenerReservas obtenerReservas;

    @Autowired
    ObtenerHorarios obtenerHorarios;

    @Autowired
    ObtenerEspacios obtenerEspacios;

    @Autowired
    ModificarEspacio modificarEspacio;

    @Autowired
    FiltrarBusquedaEspacios filtrarBusquedaEspacios;

    @Autowired
    FiltrarBusquedaReservas filtrarBusquedaReservas;

    @Autowired
    GerenteServicio gerenteServicio;

    @Autowired
    ReservaParser reservaParser;

    @Autowired
    EspacioParser espacioParser;

    private Logger log = LoggerFactory.getLogger(Receptor.class);

    private final static String COLA_ENTRADA = "entrada";
    private final static String COLA_SALIDA = "salida";
    private final static String ENV_AMQPURL_NAME = "CLOUDAMQP_URL";
    private Channel canal;

    public Receptor() throws Exception {
        ConnectionFactory factoria = new ConnectionFactory();
        String amqpURL = System.getenv(ENV_AMQPURL_NAME) != null ?
                System.getenv().get(ENV_AMQPURL_NAME) : "amqp://localhost";
                //System.getenv().get(ENV_AMQPURL_NAME) : "amqp://btguhxgi:IuGPcOaHN1LXeeXx4S0TfFMuFK50Y9hg@squid.rmq.cloudamqp.com/btguhxgi[19:31]CLOUDAMQP_URL";
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
            try {
                llamarAServicio(mensaje);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        canal.basicConsume(COLA_ENTRADA, true, deliverCallback, consumerTag -> { });
        log.info("Esperando mensajes...");
    }

    public void devolverMensajes(String mensaje) throws Exception {
        canal.basicPublish("", COLA_SALIDA, null, mensaje.getBytes());
        log.info(" [x] Enviado '" + mensaje + "'");
    }

    public void llamarAServicio(String mensaje) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject;
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String[] mensajeArray = mensaje.split(",", 2);

        switch (mensajeArray[0]) {
            // USUARIO Y GERENTE
            case "crearReserva":
                devolverMensajes(mapper.writeValueAsString(reservaParser
                        .entidadADTO(gestionReservas.crear(reservaParser
                        .DTOAEntidad(mapper.readValue(mensajeArray[1], ReservaDTO.class))))));
            break;

            //GERENTE
            case "modificarEstadoReserva":
                jsonObject = new JSONObject(mensajeArray[1]);
                Optional<Reserva> reservaOptional = gestionReservas.cambiarEstado(
                        jsonObject.getString("id"),
                        EstadoReserva.valueOf(jsonObject.getString("estado")),
                        jsonObject.getString("motivo"));
                if (reservaOptional.isPresent()) {
                    devolverMensajes(mapper.writeValueAsString(
                            reservaParser.entidadADTO(reservaOptional.get())));
                } else {
                    devolverMensajes("ERROR");
                }
            break;

            // GERENTE
            case "obtenerReservasEspacio":
                jsonObject = new JSONObject(mensajeArray[1]);
                Collection<Reserva> reservasEspacio = obtenerReservas
                        .obtenerReservasEspacio(jsonObject.getString("idEspacio"));
                devolverMensajes(mapper.writeValueAsString(reservasEspacio
                        .stream()
                        .map(reservaParser::entidadADTO)
                        .collect(Collectors.toList())));
            break;

            //GERENTE
            case "obtenerReservasEstado":
                jsonObject = new JSONObject(mensajeArray[1]);
                Collection<Reserva> reservasEstado = obtenerReservas
                        .obtenerReservasEstado(EstadoReserva.valueOf(jsonObject.getString("estado")));
                devolverMensajes(mapper.writeValueAsString(reservasEstado
                        .stream()
                        .map(reservaParser::entidadADTO)
                        .collect(Collectors.toList())));
            break;

            //??
            case "obtenerReservasEspacioEstado":
                jsonObject = new JSONObject(mensajeArray[1]);
                Collection<Reserva> reservasEspacioEstado = obtenerReservas
                        .obtenerReservasEspacioEstado(
                                jsonObject.getString("idEspacio"),
                                EstadoReserva.valueOf(jsonObject.getString("estado")));
                devolverMensajes(mapper.writeValueAsString(reservasEspacioEstado
                        .stream()
                        .map(reservaParser::entidadADTO)
                        .collect(Collectors.toList())));
            break;

            //??
            case "obtenerReservasEspacioFecha":
                jsonObject = new JSONObject(mensajeArray[1]);
                Collection<Reserva> reservasEspacioFecha = obtenerReservas
                        .obtenerPorEspacioYFecha(jsonObject.getString("idEspacio"),
                                new Timestamp(jsonObject.getLong("fecha")));
                devolverMensajes(mapper.writeValueAsString(reservasEspacioFecha
                        .stream()
                        .map(reservaParser::entidadADTO)
                        .collect(Collectors.toList())));
            break;

            //USUARIO Y GERENTE
            case "obtenerHorarioEntreFechas":
                jsonObject = new JSONObject(mensajeArray[1]);
                devolverMensajes(mapper.writeValueAsString(obtenerHorarios
                        .obtenerPorEspacioEntreFechas(jsonObject.getString("idEspacio"),
                                new Timestamp(jsonObject.getLong("fechaInicio")),
                                new Timestamp(jsonObject.getLong("fechaFin")))));
                break;

            //USUARIO
            case "obtenerEspacioPorId":
                jsonObject = new JSONObject(mensajeArray[1]);
                Optional<Espacio> espacioOptional = obtenerEspacios
                        .obtenerInformacion(jsonObject.getString("id"));
                if (espacioOptional.isPresent()) {
                    devolverMensajes(mapper.writeValueAsString(espacioParser.
                            entidadADTO(espacioOptional.get())));
                } else {
                    devolverMensajes("ERROR");
                }
                break;

            //GERENTE -> PARA MODIFICAR LOS DATOS
            case "obtenerEspacioPorEdificioYTipo":
                jsonObject = new JSONObject(mensajeArray[1]);
                Collection<Espacio> espaciosPorEdificioYTipo = obtenerEspacios
                        .obtenerPorEdificioYTipo(jsonObject.getString("edificio"),
                                jsonObject.getString("tipo"));
                devolverMensajes(mapper.writeValueAsString(espaciosPorEdificioYTipo
                        .stream()
                        .map(espacioParser::entidadADTO)
                        .collect(Collectors.toList())));
                break;

            //GERENTE
            case "modificarEspacio":
                    Optional<Espacio> espacioModificadoOptional = modificarEspacio
                            .modificar(mapper.readValue(mensajeArray[1], DatosDTO.class));
                    if (espacioModificadoOptional.isPresent()) {
                        devolverMensajes(mapper.writeValueAsString(espacioParser.
                                entidadADTO(espacioModificadoOptional.get())));
                    } else {
                        devolverMensajes("ERROR");
                    }
                break;

            //USUARIO
            case "filtrarBusquedaEspacios":
                Collection<Espacio> espaciosFiltrados = filtrarBusquedaEspacios
                        .filtrar(mapper.readValue(mensajeArray[1], BusquedaDTO.class));
                devolverMensajes(mapper.writeValueAsString(espaciosFiltrados
                        .stream()
                        .map(espacioParser::entidadADTO)
                        .collect(Collectors.toList())));
                break;

            //??
            case "filtrarBusquedaReservas":
                jsonObject = new JSONObject(mensajeArray[1]);
                Collection<Reserva> reservasFiltradas = filtrarBusquedaReservas
                        .filtrar(jsonObject.getString("edificio"),
                                jsonObject.getString("tipo"),
                                new Timestamp(jsonObject.getLong("fechaIni")),
                                new Timestamp(jsonObject.getLong("fechaFin")),
                                jsonObject.getInt("horaIni"),
                                jsonObject.getInt("horaFin"));
                devolverMensajes(mapper.writeValueAsString(reservasFiltradas
                        .stream()
                        .map(reservaParser::entidadADTO)
                        .collect(Collectors.toList())));
                break;

            //GERENTE
            case "logInGerente":
                GerenteDTO gerenteDTO = mapper.readValue(mensajeArray[1], GerenteDTO.class);
                devolverMensajes(gerenteServicio.logIn(gerenteDTO.getNomUsuario(),
                        gerenteDTO.getPassword()).toString());
                break;

            default:
                devolverMensajes("Mensaje mal formado");
            break;
        }
    }
}
