package com.alby;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.alby.controller.AccountController;
import com.alby.entity.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class AccountControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	AccountController accountController;

	@Test
	void getAllAccount() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/getAllAccount").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").exists());
	}
	
	@Test
	public void createAccount() throws Exception {
	    Account account = new Account();
	    account.setAccountName("Liu Kang");
	    account.setAccountDob(new Date());
	    account.setAccountAddress("German");
	    
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(account);

	    mockMvc.perform(post("/createAccount").contentType(MediaType.APPLICATION_JSON)
	        .content(requestJson))
	        .andExpect(status().isOk());
	}
}
