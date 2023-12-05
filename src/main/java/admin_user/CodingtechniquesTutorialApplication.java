package admin_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableConfigurationProperties
public class CodingtechniquesTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingtechniquesTutorialApplication.class, args);
	}

}
