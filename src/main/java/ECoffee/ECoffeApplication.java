package ECoffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = {"ECoffee.entities", "ECoffee.repositories", "ECoffee.sevices","ECoffee.config","ECoffee.exceptions"})
public class ECoffeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECoffeApplication.class, args);
	}

}
