package evolution.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;

@Configuration
public class AnyConfiguration {
	@Bean
	public Gson gson() {
		return new Gson();
	}
}
