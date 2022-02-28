package main.java.emlakburada.service;

import emlakburada.dto.EmailMessage;
import emlakburada.model.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ActiveMqListenerService {

    @Autowired
    private EmailService emailService;

    @JmsListener(destination = "emlakburada.queue")
    public void receiveMessage(String message) {

        EmailMessage emailMessage = new EmailMessage(message);
        Email email = new Email();
        email.setEmail(message.split(",")[0]);
        email.setUserId(Integer.parseInt(message.split(",")[1]));
       log.info(message);
       emailService.send(emailMessage.getEmail().toString().split(",")[0]);
       emailService.saveEmail(email);
    }

}
