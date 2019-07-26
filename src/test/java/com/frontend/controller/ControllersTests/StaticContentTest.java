package com.frontend.controller.ControllersTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.frontend.jobmanger.Application;

import frontend.security.config.SecurityConfiguration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, SecurityConfiguration.class})
@AutoConfigureMockMvc()
public class StaticContentTest
{
	@Autowired
	private MockMvc mockMvc;
	
    @Test
    public void checkThatStaticContentCSSCanBeAccessed() throws Exception {
    	   mockMvc.perform(get("/css/test.css"))     
    	   .andExpect(status().isOk());  	   
    }
    
    @Test
    public void checkThatStaticContentIMGCanBeAccessed() throws Exception {
    	   mockMvc.perform(get("/img/test.jpg"))     
    	   .andExpect(status().isOk());  	   
    }
    
    @Test
    public void checkThatStaticContentJSCanBeAccessed() throws Exception {
    	   mockMvc.perform(get("/js/test.js"))     
    	   .andExpect(status().isOk());  	   
    }
}
