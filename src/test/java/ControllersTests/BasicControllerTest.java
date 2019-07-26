/******************************************
 Created by: RSUBRAM a.k.a angoothachap
 Created on: 7/2/2019 1:36 PM
 ******************************************/

package ControllersTests;

import com.frontend.jobmanger.Application;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
@WebMvcTest()
@AutoConfigureMockMvc
public class BasicControllerTest {
    protected MockMvc mockMvcLogin;
    
    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void setUp() {
        mockMvcLogin = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

}
