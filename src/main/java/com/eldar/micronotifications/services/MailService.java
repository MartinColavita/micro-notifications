package com.eldar.micronotifications.services;

import com.eldar.micronotifications.model.MailRequestDTO;

public interface MailService {
    void sendMail(MailRequestDTO mailRequestDTO);
    void testSendMail();
}
