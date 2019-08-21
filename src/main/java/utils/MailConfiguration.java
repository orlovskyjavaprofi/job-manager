package utils;

import java.util.Properties;

import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration
{
	  @Bean
	  public JavaMailSender getJavaMailSender() {
		  JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		    mailSender.setHost("localhost");
		    mailSender.setPort(7777);
		     
// this for production only!		    
//		    mailSender.setUsername("my.gmail@gmail.com");
//		    mailSender.setPassword("password");
		     
		    Properties props = mailSender.getJavaMailProperties();
		    props.put("mail.transport.protocol", "smtp");
//		    props.put("mail.smtp.auth", "true");
//		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.debug", "true");
		     
		    return mailSender;
	  }
	 
	 
}
