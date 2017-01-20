package ro.rocket_team.vbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = SpringDataWebAutoConfiguration.class)
@EnableScheduling
public class Application {

	public static void main(final String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}
