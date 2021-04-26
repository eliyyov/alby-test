package com.alby;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
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

import com.alby.common.TransactionType;
import com.alby.controller.TransactionController;
import com.alby.entity.Account;
import com.alby.entity.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class TransactionControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	TransactionController transactionController;
	
	@Test
	void getAllAccount() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/getAllTransaction").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").exists());
	}
	
	@Test
	public void doTransaction() throws Exception {

		JSONObject jsonObject = new JSONObject();
		int accountId = 1;
		int transactionType = 0;
		double transactionAmount = 100.0;
		Date transactionDate = new Date();
		
		jsonObject.put("accountId", accountId);
		jsonObject.put("transactionType", transactionType);
		jsonObject.put("transactionAmount", transactionAmount);
		jsonObject.put("transactionDate", transactionDate);
	    
	    mockMvc.perform(post("/doTransaction").contentType(MediaType.APPLICATION_JSON)
	        .content(jsonObject.toString()))
	        .andExpect(status().isOk());
	}
	
	@Test
	public void checkTransactionByDate() throws Exception {

		JSONObject jsonObject = new JSONObject();
		String startDate = "2019-01-01";
		String endDate = "2021-01-01";
		
		jsonObject.put("startDate", startDate);
		jsonObject.put("endDate", endDate);
	    
	    mockMvc.perform(post("/checkTransactionByDate").contentType(MediaType.APPLICATION_JSON)
	        .content(jsonObject.toString()))
	        .andExpect(status().isOk());
	}
	
	@Test
	public void checkTransactionById() throws Exception {

		JSONObject jsonObject = new JSONObject();
		int accountId = 1;
		
		jsonObject.put("accountId", accountId);
	    
	    mockMvc.perform(post("/checkTransactionByDate").contentType(MediaType.APPLICATION_JSON)
	        .content(jsonObject.toString()))
	        .andExpect(status().isOk());
	}


}
