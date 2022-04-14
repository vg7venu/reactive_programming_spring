package learn.reactive.react;

import learn.reactive.react.service.AlfredService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReactApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactApplication.class, args);
	}

	@Bean("alfredService")
	public AlfredService getBean(){
		return new AlfredService();
	}
}
