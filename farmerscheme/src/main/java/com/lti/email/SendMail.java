package com.lti.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class SendMail {

	@Autowired
	private MailSender mailSender;

	public void SuccessMail(String toMail, String name, String role) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("cmakhilesh11@gmail.com");
		message.setTo(toMail);
		message.setSubject("Registration Successfull");
		message.setText("Congratulations " + name + "\n" + "You have been successfully registered as " + role
				+ " and your account has been activated");
		mailSender.send(message);

	}

	// User Registration Registration Declined
	public void DeclinedMail(String toMail, String name, String role) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("cmakhilesh11@gmail.com");
		message.setTo(toMail);
		message.setSubject("Registration declined");
		message.setText("Hello " + name + "Your attempt to register as " + role
				+ " in our scheme has been rejected while document verification. Awaiting for a new registration with proper documents ");
		mailSender.send(message);
	}

	public void otpMail(String toMail, String text, String subject) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("cmakhilesh11@gmail.com");
		message.setTo(toMail);
		message.setSubject(subject);
		message.setText(text);
		System.out.println(message);
		mailSender.send(message);
		System.out.println("**");
	}

	public void CropAccept(String emailId, int cropId, String cropName) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("cmakhilesh11@gmail.com");
		message.setTo(emailId);
		message.setSubject("Crop Sell Request Approved");
		message.setText("Congratulations!Your crop sell request accepted for crop " + cropName + " and your crop ID is "
				+ cropId);
		System.out.println(message);
		mailSender.send(message);
	}

	public void CropReject(String emailId, String cropName) {
		// TODO Auto-generated method stub
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("cmakhilesh11@gmail.com");
		message.setTo(emailId);
		message.setSubject("Crop Sell Request Declined");
		message.setText("Your crop sell request is declined for crop " + cropName);
		System.out.println(message);
		mailSender.send(message);
	}

	public void bidSuccessFarmer(String emailId, int cropId, String cropName, double cropSoldPrice) {
		// TODO Auto-generated method stub
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("cmakhilesh11@gmail.com");
		message.setTo(emailId);
		message.setSubject("Crop Sold and Amount transferred");
		message.setText("Congratulations!Your crop " + cropName + " with crop ID " + cropId + " has been sold for "
				+ cropSoldPrice + "and respective payments were reflected to your account");
		System.out.println(message);
		mailSender.send(message);
	}

	public void bidSuccessBidder(String emailId, int cropId, String cropName, double cropSoldPrice, int bidId) {
		// TODO Auto-generated method stub
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("cmakhilesh11@gmail.com");
		message.setTo(emailId);
		message.setSubject("Bid Successfull and Payment received");
		message.setText("Congratulations!Your bid " + bidId + " for crop " + cropName + " with crop ID " + cropId
				+ " was successfull with bid amount of " + cropSoldPrice + "and payment process successful");
		System.out.println(message);
		mailSender.send(message);

	}

	public void claimSuccess(String emailId, int policyId) {
		// TODO Auto-generated method stub
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("cmakhilesh11@gmail.com");
		message.setTo(emailId);
		message.setSubject("Claim approved for insurance");
		message.setText("Your request to claim insurance for policy " + policyId
				+ " was successfull and payment will processed in two working days");
		System.out.println(message);
		mailSender.send(message);
	}

	public void claimDeclined(String emailId, int policyId) {
		// TODO Auto-generated method stub
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("cmakhilesh11@gmail.com");
		message.setTo(emailId);
		message.setSubject("Claim declined for insurance");
		message.setText("Your request to claim insurance for policy " + policyId
				+ " was declined due to inappropriate documents");
		System.out.println(message);
		mailSender.send(message);
	}

	public void bidGoneBidder(String emailId, int cropId, String cropName, String string, int bidId) {
		// TODO Auto-generated method stub
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("cmakhilesh11@gmail.com");
		message.setTo(emailId);
		message.setSubject("Bid Gone");
		message.setText("Your bid for crop "+cropName+" of id "+cropId+" was gone. So bidding session for "+ cropId+" is completed and sold");
		System.out.println(message);
		mailSender.send(message);
		
	}

}
