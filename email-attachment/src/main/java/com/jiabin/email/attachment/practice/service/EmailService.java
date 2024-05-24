package com.jiabin.email.attachment.practice.service;

import com.jiabin.email.attachment.practice.entity.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailService {
	
	@Value("${spring.mail.username}")
	private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendEmailWithAttachment(String to, String subject, String username, List<Attachment> attachments) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);

        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("attachments", attachments);
        String content = templateEngine.process("email-template", context);

        helper.setText(content, true);

        for (Attachment attachment : attachments) {
            helper.addAttachment(attachment.getName(), new ByteArrayResource(attachment.getData()), attachment.getContentType());
        }

        javaMailSender.send(message);
    }
}