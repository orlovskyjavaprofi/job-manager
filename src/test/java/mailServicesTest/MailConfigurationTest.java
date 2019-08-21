package mailServicesTest;

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

import utils.MailConfiguration;

//in order to execute this test successfully you need 2 things free 7777 port and
//running fakesmtp server , which you can obtain from http://nilhcem.com/FakeSMTP/screenshots.html
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
class MailConfigurationTest
{
	@Autowired
	MailConfiguration mailConfig;
	

	  @BeforeEach
	  void setup() {
		  if (mailConfig == null)
			{
			  mailConfig = new MailConfiguration();
			}
	  }
	  
	  @Test
	  void checkIfinJavaMailConfigExist(){
		  assertNotNull(mailConfig,"Mail configuration was not created!");
	  }
}
