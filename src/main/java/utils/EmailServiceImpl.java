package utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService
{
	@Autowired
	public JavaMailSender emailSender;
	
	@Override
	public boolean sendSimpleEmailMessage(String toRecipient, String subjectOfEamil, String bodyOfEmail)
	{ 
		boolean result = false;
		SimpleMailMessage newEmail;
		if( (toRecipient.isEmpty()== false) && (subjectOfEamil.isEmpty() == false)
				&& (bodyOfEmail.isEmpty() == false) ) {
			newEmail = new SimpleMailMessage(); 
			newEmail.setTo(toRecipient);
			newEmail.setSubject(subjectOfEamil);
			newEmail.setText(bodyOfEmail);
			emailSender.send(newEmail);
			result = true;
		}
		
		return result;
	}
	
}
