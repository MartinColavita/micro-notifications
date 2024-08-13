package com.eldar.micronotifications.model.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
@Data
public class WspRequestDTO {
    private String content;
    private String phoneNumber;
    private String webHookEndpoint; // endpoint de la aplicacion que recibe el mail
    private Integer expiration;
}