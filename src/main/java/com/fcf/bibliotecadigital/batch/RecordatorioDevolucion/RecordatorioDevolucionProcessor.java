package com.fcf.bibliotecadigital.batch.RecordatorioDevolucion;

import com.fcf.bibliotecadigital.model.Prestamo;
import com.fcf.bibliotecadigital.util.EmailService;
import com.fcf.bibliotecadigital.util.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordatorioDevolucionProcessor<P, P1> implements ItemProcessor<Prestamo, Prestamo> {

    private static final Logger log = LoggerFactory.getLogger(RecordatorioDevolucionProcessor.class);

    @Autowired
    private EmailService emailService;

    @Override
    public Prestamo process(Prestamo prestamo) throws Exception {
        log.info("Enviando emails..");
        final String subject = "Recordatorio de devolucion de libro";

        try {
            //CREO UNA INSTANCIA DE LA CLASE UTILITARIA MAIL Y SETEO EL OBJETO
            Mail mail = new Mail();
            mail.setFrom("gonzalo_delcorro@hotmail.com");
            mail.setTo(prestamo.getAlumno().getCorreo());
            mail.setSubject(subject);

            //GENERO EL MODELO DE CORREO PARA INCRUSTAR EN LA PLANTILLA DE THYMELEAF USANDO UN MAP
            Map<String, Object> model = new HashMap<>();
            model.put("alumno", "Hola, " + prestamo.getAlumno().getNombres() + " " + prestamo.getAlumno().getApellidos());
            model.put("libro", prestamo.getLibro().getTitulo());
            model.put("fechaPrestamo", prestamo.getFechaPrestamo());
            mail.setModel(model);
            emailService.sendEmail(mail);

        } catch (Exception e) {
            log.info("Error en el envío de mail");
        }
        return prestamo;
    }
}
