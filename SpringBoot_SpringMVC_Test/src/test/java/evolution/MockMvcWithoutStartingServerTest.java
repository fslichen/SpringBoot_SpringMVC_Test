package evolution;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import evolution.controller.dto.AnyDto;
import evolution.service.AnyService;

// In this test, the full Spring application context is started, but without the server. 
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcWithoutStartingServerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private AnyService anyService;
	
	@Autowired
	private Gson gson;
	
	@Test
	public void testAnyMethod() {
		assert anyService.anyMethod().equals("Hello World");
	}
	
	@Test
	public void testGet() throws Exception {
		mockMvc.perform(get("/get"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.name").value("Chen"))
		.andExpect(jsonPath("$.age").value(27));
	}
	
	@Test
	public void testPost() throws Exception {
		AnyDto anyDto = new AnyDto();
		anyDto.setName("Chen");
		anyDto.setAge(27);
		mockMvc.perform(post("/post", anyDto)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(gson.toJson(anyDto)))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name").value("Chen"))
		.andExpect(jsonPath("$.age").value(27));
	}
}
