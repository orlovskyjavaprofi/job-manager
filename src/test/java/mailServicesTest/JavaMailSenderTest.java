package mailServicesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.frontend.jobmanger.Application;

import utils.EmailServiceImpl;

// in order to execute this test successfully you need 2 things free 7777 port and
// running fakesmtp server , which you can obtain from http://nilhcem.com/FakeSMTP/screenshots.html
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
class JavaMailSenderTest
{
	@Autowired
	private EmailServiceImpl mailSender;
	
	  @BeforeEach
  	  void setup() {
		if (mailSender == null)
		{
			mailSender = new EmailServiceImpl();
		}
	  }
	  
	  @Test
	  void checkIfinJavaMailSenderExist(){
		  assertNotNull(mailSender,"Email service was not created!");
	  }
	  
	  @Test
	  void checkIfSimpleEmailCanBeSent() {
		 boolean expectedResultOfSent = true;
		 String toRecipient = "javaprofi@gmx.de";
		 String subjectOfEamil = "test";
		 String bodyOfEmail = "This is a simple email send from Spring Boot server";
		 boolean actualResult = mailSender.sendSimpleEmailMessage(toRecipient,subjectOfEamil,bodyOfEmail);
		 assertEquals(expectedResultOfSent, actualResult, "The email wasnt send successfully!");
	  }
	  
}
