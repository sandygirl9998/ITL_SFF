package com.lti.email;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	//User Registration Registration Approved
		public static void SuccessMail(String toMail, String password, String name, String role) {
			String host = "smtp.office365.com";
			String user = "schemeforfarmers@outlook.com";
			String pass = "scheme12345";
			String to = toMail; // toMail;
			String from = "schemeforfarmers@outlook.com"; // "your email";
			String subject = "User Approved"; // subjectMail;
			String messageText = "Congratulations " + name + "\n" + "You have been successfully registered as " + role
					+ " and your account has been activated"; // context;
			boolean sessionDebug = false;

			Properties props = System.getProperties();

			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.required", "true");
			java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);
			try {
				Message msg = new MimeMessage(mailSession);
				msg.setFrom(new InternetAddress(from));
				InternetAddress[] address = { new InternetAddress(to) };
				msg.setRecipients(Message.RecipientType.TO, address);
				msg.setSubject(subject);
				msg.setSentDate(new Date());
				msg.setText(messageText);
				Transport transport = mailSession.getTransport("smtp");
				transport.connect(host, user, pass);
				transport.sendMessage(msg, msg.getAllRecipients());
				transport.close();
				System.out.println("message send successfully");
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}

		}
		
		//User Registration Registration Declined
			public static void DeclinedMail(String toMail, String password, String name, String role) {
				String host = "smtp.office365.com";
				String user = "schemeforfarmers@outlook.com";
				String pass = "scheme12345";
				String to = toMail; // toMail;
				String from = "schemeforfarmers@outlook.com"; // "your email";
				String subject = "Registration Declined"; // subjectMail;
				String messageText = "Hello " + name + "\n" + "Your attempt to register as " + role
						+ " in our scheme has been rejected while document verification. Awaiting for a new registration with proper documents "; // context;
				boolean sessionDebug = false;

				Properties props = System.getProperties();

				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.port", "587");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.required", "true");
				java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
				Session mailSession = Session.getDefaultInstance(props, null);
				mailSession.setDebug(sessionDebug);
				try {
					Message msg = new MimeMessage(mailSession);
					msg.setFrom(new InternetAddress(from));
					InternetAddress[] address = { new InternetAddress(to) };
					msg.setRecipients(Message.RecipientType.TO, address);
					msg.setSubject(subject);
					msg.setSentDate(new Date());
					msg.setText(messageText);
					Transport transport = mailSession.getTransport("smtp");
					transport.connect(host, user, pass);
					transport.sendMessage(msg, msg.getAllRecipients());
					transport.close();
					System.out.println("message send successfully");
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}

			}

}
