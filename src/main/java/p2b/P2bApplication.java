package p2b;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import p2b.entity.PageEntity;
import p2b.entity.Status;
import p2b.repository.PageRepository;

@SpringBootApplication
@RestController
public class P2bApplication {
    private static final Logger log = LoggerFactory.getLogger("Application log");

    public static void main(String[] args) {
        SpringApplication.run(P2bApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(PageRepository repository) {
        return args -> {
            log.info("Preloading: {}", repository.save(new PageEntity(1, "Page #1", Status.PRIVATE, "this is the first page!")));
            log.info("Preloading: {}", repository.save(new PageEntity(2, "Page #2", Status.PUBLIC, "this is the second page!")));
        };
    }
}
