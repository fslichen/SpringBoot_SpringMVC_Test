package evolution;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import evolution.controller.dto.AnyDto;
import evolution.service.AnyService;

// Spring Boot is only instantiating the web layer, not the whole context.
@RunWith(SpringRunner.class)
@WebMvcTest
public class MockMvcWithoutStartingContext {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private Gson gson;
	
//	@Autowired AnyService cannot be autowired because only the web layer is instantiated. 
	private AnyService anyService;
	
	@Test
	public void testContext() {
		assert gson != null;
	}
	
	@Test
	public void testGet() throws Exception {
		mockMvc.perform(get("/get"))
		.andExpect(jsonPath("$.name").value("Chen"))
		.andExpect(jsonPath("$.age").value(27));
	}
	
	@Test
	public void testPost() throws Exception {
		AnyDto anyDto = new AnyDto();
		anyDto.setName("Chen");
		anyDto.setAge(27);
		mockMvc.perform(post("/post")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(new Gson().toJson(anyDto)))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.name").value("Chen"))
		.andExpect(jsonPath("$.age").value(27));
	}
}
