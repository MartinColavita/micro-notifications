package com.eldar.micronotifications.services;

import com.eldar.micronotifications.model.requests.MailRequestDTO;

public interface MailService {
    void sendMail(MailRequestDTO mailRequestDTO);
    void testSendMail();
}
