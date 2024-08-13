package com.eldar.micronotifications.services;

import com.eldar.micronotifications.model.MailRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Slf4j
@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String destinatario;

    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void sendMail(MailRequestDTO mailRequestDTO) {
        if (mailRequestDTO == null || mailRequestDTO.getTo() == null || mailRequestDTO.getContent() == null || mailRequestDTO.getSubject() == null || mailRequestDTO.getWebHookEndpoint() == null) {
            throw new IllegalArgumentException("Mail request is missing required fields.");
        }

        log.info("#### Enviando mail a: " + mailRequestDTO.getTo());
        log.info("#### Contenido: " + mailRequestDTO.getContent());
        log.info("#### Asunto: " + mailRequestDTO.getSubject());
        log.info("#### Endpoint: " + mailRequestDTO.getWebHookEndpoint());
        log.info("#### Expiracion: " + mailRequestDTO.getExpiration());

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(destinatario);
        message.setTo(mailRequestDTO.getTo());
        message.setSubject(mailRequestDTO.getSubject());
        message.setText(mailRequestDTO.getContent());

        log.info("----> llamado a javaMailSender");
        javaMailSender.send(message);
        log.info("#### Correo enviado exitosamente ####");
    }

    public void testSendMail() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(destinatario);
        message.setTo("martincolavita@gmail.com");
        message.setSubject("Asunto de prueba");
        message.setText("mensaje de prueba");

        log.info("----> llamado a javaMailSender");
        javaMailSender.send(message);
        log.info("#### Correo enviado exitosamente ####");
    }

}