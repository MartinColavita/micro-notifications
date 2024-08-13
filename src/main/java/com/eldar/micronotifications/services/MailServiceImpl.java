package com.eldar.micronotifications.services;

import com.eldar.micronotifications.model.requests.MailRequestDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.Date;

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

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(destinatario);
        message.setTo(mailRequestDTO.getTo());
        message.setSubject(mailRequestDTO.getSubject());
        message.setText(mailRequestDTO.getContent());
        message.setSentDate(new Date());

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
        message.setSentDate(new Date());

        log.info("----> llamado a javaMailSender");
        javaMailSender.send(message);
        log.info("#### Correo enviado exitosamente ####");
    }

}