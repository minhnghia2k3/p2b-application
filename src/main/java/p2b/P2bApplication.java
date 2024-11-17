package p2b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class P2bApplication {

	public static void main(String[] args) {
		SpringApplication.run(P2bApplication.class, args);
	}

	@GetMapping("/")
	public String sayHello() {
		return "Hello ửold";
	}

}
