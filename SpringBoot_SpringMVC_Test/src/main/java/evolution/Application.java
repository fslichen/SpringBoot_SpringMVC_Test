package evolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// https://spring.io/guides/gs/testing-web/
// @Configuration tags the class as a source of bean definitions for the application context.
// @EnableAutoConfiguration tells Spring Boot to start adding beans based on classpath settings.
// @ComponentScan tells Spring to look for other components, configurations, and services in the the package.
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		// SpringApplication.run(Application.class, args) works as well.
		SpringApplication.run(Application.class);
	}
}
