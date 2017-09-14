package evolution;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import evolution.controller.AnyController;
import evolution.controller.dto.AnyDto;
import evolution.service.AnyService;

// The entire server is started.
// If you have multiple methods in a test case, or multiple test cases with the same configuration, they only incur the cost of starting the application once.
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)// Tells Spring Boot to go and look for a main configuration class (one with @SpringBootApplication for instance), and use that to start a Spring application context. 
public class ApplicationTest {
	@LocalServerPort
	private int port;
	
	@Autowired
	private AnyController anyController;
	
	@Autowired
	private AnyService anyService; 
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Value("${name}")
	private String name;// Reads the properties from application.properties under test rather than main source folder.
	
	@Test
	public void testPortNumber() {
		System.out.println("The name attribute from application.properties = " + name);
		System.out.println("The random server port number = " + port);
	}
	
	@Test
	public void testAnyControllerIsNotNull() {
		assertThat(anyController).isNotNull();
	}
	
	@Test
	public void testAnyMethod() {
		assert anyService.anyMethod().equals("Hello World");
	}
	
	@Test
	public void testPost() {
		String url = "http://localhost:" + port + "/post";
		AnyDto anyDto = new AnyDto();
		anyDto.setName("Chen");
		anyDto.setAge(27);
		assert restTemplate.postForObject(url, anyDto, AnyDto.class).getName().equals("Chen");
	}
}
