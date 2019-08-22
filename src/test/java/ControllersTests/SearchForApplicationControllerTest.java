package ControllersTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
class SearchForApplicationControllerTest extends BasicControllerTest
{
	@Test
	public void checkSearchJobApplNotReachableForAnon() throws Exception
	{
		mockMvcLogin.perform(get("/memberarea/userAccountOffice/searchForUserApplications"))
		.andExpect(status().isForbidden());
	}
	
}
