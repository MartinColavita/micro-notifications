package com.eldar.micronotifications.controller;

import com.eldar.micronotifications.model.requests.MailRequestDTO;
import com.eldar.micronotifications.services.MailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(value = "*")
@RestController
@RequestMapping("/api/mail")
@AllArgsConstructor
public class MailController {

    private final MailService mailService;


    @PostMapping("/send")
    @Operation(
            summary = "Enviar un correo electrónico",
            description = "Este endpoint acepta un objeto JSON que contiene la información del correo electrónico y envía el correo.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MailRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo 1",
                                            value = "{\"content\": \"Hola, este es un correo de prueba al mail de eldar.\", \"to\": \"martin.colavita@eldars.com.ar\", \"webHookEndpoint\": \"http://ejemplo/webhook\", \"expiration\": 60, \"subject\": \"Prueba de correo\"}"
                                    ),
                                    @ExampleObject(
                                            name = "Ejemplo 2",
                                            value = "{\"content\": \"Este es otro correo de prueba a gmail.\", \"to\": \"martincolavita@gmail.com\", \"webHookEndpoint\": \"http://ejemplo/webhook\", \"expiration\": 120, \"subject\": \"Otro asunto de prueba\"}"
                                    )
                            }
                    )
            )
    )
    public ResponseEntity<MailRequestDTO> envioMail(@RequestBody MailRequestDTO mailRequestDTO) {
        log.info("#### Comienza endpoint: /send-mail ####");
        log.info("MailRequestDTO recibido: " + mailRequestDTO.toString());

        if (mailRequestDTO.getTo() == null || mailRequestDTO.getContent() == null || mailRequestDTO.getSubject() == null || mailRequestDTO.getWebHookEndpoint() == null) {
            log.error("#### Error: Missing required fields in MailRequestDTO ####");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        mailService.sendMail(mailRequestDTO);
        return new ResponseEntity<>(mailRequestDTO, HttpStatus.OK);
    }


    @PostMapping("/test-send")
    public ResponseEntity<String> testEnvioMail() {
        log.info("#### Comienza endpoint: /test-send-mail ####");
        try {
            mailService.testSendMail();
            return new ResponseEntity<>("Correo de prueba enviado exitosamente.", HttpStatus.OK);
        } catch (Exception e) {
            log.error("#### Error al enviar el correo de prueba: " + e.getMessage() + " ####");
            return new ResponseEntity<>("Error al enviar el correo de prueba.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
