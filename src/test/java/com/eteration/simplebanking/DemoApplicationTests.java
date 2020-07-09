package com.eteration.simplebanking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.eteration.simplebanking.controller.Amount;
import com.eteration.simplebanking.dto.AccountDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void a_create() throws Exception {
		AccountDto accountDto = new AccountDto("Kerem Karaca", "123");
		ObjectMapper Obj = new ObjectMapper();
		String content = Obj.writeValueAsString(accountDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account/v1/create")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(content);

//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		System.out.println(result.getResponse());

		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.owner").value("Kerem Karaca"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value("123"));

	}

	@Test
	public void b_getAccount() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account/v1/get/123")
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.owner").value("Kerem Karaca"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value("123"));

	}

	@Test
	public void credit() throws Exception {
		Amount amount = new Amount();
		amount.setAmount(1000.0);
		ObjectMapper Obj = new ObjectMapper();
		String content = Obj.writeValueAsString(amount);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account/v1/credit/123")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(content);

		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.approvalCode").exists());

	}

	@Test
	public void debit() throws Exception {
		Amount amount = new Amount();
		amount.setAmount(500.0);
		ObjectMapper Obj = new ObjectMapper();
		String content = Obj.writeValueAsString(amount);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account/v1/debit/123")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(content);

		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.approvalCode").exists());

	}
	
	@Test
	public void e_checkAccount() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account/v1/get/123")
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.owner").value("Kerem Karaca"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value("123"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.balance").value("500.0"));

	}

}
