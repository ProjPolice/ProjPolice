package com.projpolice.global.notification.mail.service;

import java.security.SecureRandom;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.projpolice.global.notification.mail.dto.MailDto;

import jakarta.mail.Address;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final String senderId;

    private final String senderPw;

    private final String smtpHost;

    private final String smtpPort;

    private final Authenticator auth;
    private final String encoding = "utf-8";

    private final String senderEmail;

    private final String domain;

    private final boolean startTLSEnable;

    private final boolean enableSSL;

    private final String nickname;

    private final SecureRandom random = new SecureRandom();

    @Autowired
    MailServiceImpl(Environment env) {
        senderEmail = env.getProperty("projpolice.mail.sender");
        senderId = env.getProperty("projpolice.mail.username");
        senderPw = env.getProperty("projpolice.mail.password");
        smtpHost = env.getProperty("projpolice.mail.host");
        smtpPort = env.getProperty("projpolice.mail.port");
        domain = env.getProperty("projpolice.mail.domain");
        nickname = env.getProperty("propolice.mail.nickname");
        String tmp = env.getProperty("projpolice.mail.properties.mail.smtp.starttls.enable");
        if (tmp == null) {
            tmp = "";
        }
        startTLSEnable = "true".equalsIgnoreCase(tmp);
        tmp = env.getProperty("projpolice.mail.properties.mail.smtp.ssl.enable");
        if (tmp == null) {
            tmp = "";
        }
        enableSSL = "true".equalsIgnoreCase(tmp);

        auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderId, senderPw);
            }
        };
    }

    @Async
    @Override
    public void sendMail(MailDto mail) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", smtpHost);
        prop.put("mail.smtp.port", Integer.parseInt(smtpPort));
        prop.put("mail.smtp.starttls.enable", startTLSEnable);
        prop.put("mail.smtp.EnableSSL.enable", enableSSL);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.quitwait", "false");
        prop.put("mail.mime.allowutf8", true);
        Session session = Session.getDefaultInstance(prop, auth);

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(String.format("%s <%s>", nickname, senderEmail));
            message.setHeader("Message-ID", String.format("%d@%s", random.nextLong(), domain));
            Address[] recipientAddress = InternetAddress.parse(mail.getEmailAddress());

            for (Address address : recipientAddress) {
                message.addRecipient(Message.RecipientType.TO, address);
            }
            message.setSubject(mail.getTitle(), encoding);
            message.setText(mail.getBody(), encoding);
            Transport.send(message);
        } catch (Exception e) {
            log.error("email error : {}", e.getMessage());
        }
    }
}
