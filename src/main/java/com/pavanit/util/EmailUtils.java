package com.pavanit.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender mailSender;

	public Boolean sendEmail(String to, String subject, String Body) {

		boolean isSent = false;

		try {

			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(Body, true);
			mailSender.send(mimeMessage);
			isSent = true;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return isSent;

	}

}
