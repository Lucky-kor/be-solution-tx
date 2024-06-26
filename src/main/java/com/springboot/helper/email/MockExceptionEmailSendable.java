package com.springboot.helper.email;

import org.springframework.context.annotation.Primary;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MockExceptionEmailSendable implements EmailSendable {
    @Override
    public void send(String message) throws InterruptedException {
        Thread.sleep(5000L);
        throw new MailSendException("error while send email");
    }
}
