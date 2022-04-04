package web.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import web.model.User;

@SpringBootApplication
@Configuration
@ComponentScan("web")
@EnableTransactionManagement
@EnableWebMvc
@PropertySource("classpath:application.properties")
@EntityScan(basePackages = {"web.model"})
public class Pp231Application {

	public static void main(String[] args) {
		SpringApplication.run(Pp231Application.class, args);
	}

}
