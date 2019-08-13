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
class PrintViewJobApplControllerTest extends BasicControllerTest
{
	@Test
	public void checkPrintViewJobApplNotReachableForAnon() throws Exception
	{
		mockMvcLogin.perform(get("/memberarea/printAllUserJobAppls"))
		.andExpect(status().isForbidden());
	}
}
