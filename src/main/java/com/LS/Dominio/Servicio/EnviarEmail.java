package com.LS.Dominio.Servicio;

import com.LS.Dominio.Entidad.Espacio;
import com.LS.Dominio.Entidad.Reserva;
import Enum.Dia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnviarEmail {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public ObtenerEspacios obtenerEspacios;

    public void enviarReservaCreada(Reserva reserva) {
        Optional<Espacio> espacioOptional  = obtenerEspacios.obtenerInformacion(reserva.getIdEspacio());
        if (espacioOptional.isPresent()) {
            Espacio espacio = espacioOptional.get();
            String asunto = "Reserva del espacio " + espacio.getNombre() + " creada";
            String texto = "Estimado/a " + reserva.getUsuario().getNombre() + ", su reserva del espacio " +
                    espacio.getNombre() + " ha sido tramitada y esta a la espera de que un gerente la valide. " +
                    "Recibirá otro correo electrónico cuando su reserva sea aceptada o rechazada.\n\n" +
                    "Información de la reserva: \n\nEspacio: " + espacio.getNombre() + "\nUbicacion: " +
                    espacio.getUbicacion().getEdificio() + ", planta " + espacio.getUbicacion().getPlanta() +
                    "\nCapacidad: " + espacio.getCapacidad() + "\n";
            if (reserva.getFechaFin().getDate() == reserva.getFechaInicio().getDate() + 1 &&
                    reserva.getFechaFin().getMonth() == reserva.getFechaInicio().getMonth() &&
                    reserva.getFechaFin().getYear() == reserva.getFechaInicio().getYear()) {
                int mes = reserva.getFechaInicio().getMonth() + 1;
                int anio = reserva.getFechaInicio().getYear() + 1900;
                texto = texto.concat("Fecha: " + reserva.getFechaInicio().getDate() + "/" +
                        mes + "/" + anio + "\nDe " + reserva.getHoraInicio() + "h a " +
                        reserva.getHoraFin() + 1 + "h\n");
            } else {
                int mesInicio = reserva.getFechaInicio().getMonth() + 1;
                int anioInicio = reserva.getFechaInicio().getYear() + 1900;
                int mesFin = reserva.getFechaFin().getMonth() + 1;
                int anioFin = reserva.getFechaFin().getYear() + 1900;
                texto = texto.concat("Fecha Inicio: " + reserva.getFechaInicio().getDate() + "/" +
                        mesInicio + "/" + anioInicio +
                        "\nFecha Fin: " + reserva.getFechaFin().getDate() + "/" +
                        mesFin + "/" + anioFin + "\nDias: ");
                for (Dia dia: reserva.getDias()) {
                    texto = texto.concat("\n" + dia);
                }
                int horaFin = reserva.getHoraFin() + 1;
                texto = texto.concat("\nDe " + reserva.getHoraInicio() + "h a " + horaFin + "h\n");
            }
            enviar(reserva.getUsuario().getEmail(), asunto, texto);
        }
    }

    private void enviar(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

}
