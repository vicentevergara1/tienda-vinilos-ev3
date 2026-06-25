package duoc.vinilos4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Vinilos4Application {
	public static void main(String[] args) {
		SpringApplication.run(Vinilos4Application.class, args);
	}
}