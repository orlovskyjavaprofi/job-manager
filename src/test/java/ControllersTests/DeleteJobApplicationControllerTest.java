package ControllersTests;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
class DeleteJobApplicationControllerTest extends BasicControllerTest {

	@Test
	public void checkThatDeleteJobApplicationIsNotReachableForAnonymNothAuthUsers() throws Exception{
		 mockMvcLogin.perform(get("/memberarea/userAccountOffice/deleteApp"))
         .andExpect(status().isForbidden());
	}

}
