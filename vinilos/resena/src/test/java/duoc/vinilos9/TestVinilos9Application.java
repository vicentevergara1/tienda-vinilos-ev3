package duoc.vinilos9;

import org.springframework.boot.SpringApplication;

public class TestVinilos9Application {

	public static void main(String[] args) {
		SpringApplication.from(Vinilos9Application::main).with(TestcontainersConfiguration.class).run(args);
	}

}
