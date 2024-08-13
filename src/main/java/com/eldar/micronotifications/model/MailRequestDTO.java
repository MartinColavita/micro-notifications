package com.eldar.micronotifications.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor @NoArgsConstructor
public class MailRequestDTO {
    @JsonProperty("content")
    private String content;              // contenido del mail
    @JsonProperty("to")
    private String to;
    @JsonProperty("webHookEndpoint")
    private String webHookEndpoint;     // endpoint de la aplicacion que recibe el mail
    @JsonProperty("expiration")
    private Integer expiration ;        // tiempo de expiracion del token
    @JsonProperty("subject")
    private String subject;
}
