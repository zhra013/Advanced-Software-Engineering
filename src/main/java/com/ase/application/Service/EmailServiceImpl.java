package com.ase.application.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements  EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

        public void SendEmailWithAttach()
        {

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo("shravan1997_patel@yahoo.com");

            msg.setSubject("Testing from Spring Boot");
            msg.setText("Hello World \n Spring Boot Email");

            javaMailSender.send(msg);
        }

}
