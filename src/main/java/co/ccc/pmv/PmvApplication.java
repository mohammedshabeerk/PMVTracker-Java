package co.ccc.pmv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EntityScan(basePackages = "co.ccc.pmv.entity")
@ComponentScan(basePackages = {"co.ccc.pmv"})
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class PmvApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmvApplication.class, args);
	}

}
