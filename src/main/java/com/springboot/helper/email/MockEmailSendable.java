package com.springboot.helper.email;


import org.springframework.stereotype.Component;

@Component
public class MockEmailSendable implements EmailSendable {
    @Override
    public void send(String message) {
        System.out.println("Sent email");
    }
}
