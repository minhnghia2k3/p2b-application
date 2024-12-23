package p2b;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import p2b.entity.*;
import p2b.repository.BookRepository;
import p2b.repository.PageRepository;
import p2b.repository.PermissionRepository;
import p2b.repository.UserRepository;
import p2b.service.FileStorageService;

import java.time.LocalDate;

@SpringBootApplication
@RestController
public class P2bApplication {
    private static final Logger log = LoggerFactory.getLogger("Application log");

    public static void main(String[] args) {
        SpringApplication.run(P2bApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(PageRepository repository, BookRepository bookRepository, FileStorageService storageService,
                             PermissionRepository permissionRepository) {
        return args -> {
            var page1 = repository.save(new PageEntity(1, "Page #1", Status.PRIVATE, "this is the first page!"));
            log.info("Preloading: {}", page1);
            log.info("Preloading: {}", repository.save(new PageEntity(2, "Page #2", Status.PUBLIC, "this is the second page!")));
            log.info("Preloading: {}", bookRepository.save(new BookEntity(1, "Book #1", "Author of the book", LocalDate.now(), "https://google.com", page1)));

            // insert permission
            log.info("Preloading: {}", permissionRepository.save(new PermissionEntity(PermissionCode.PAGE_READ, true)));
            log.info("Preloading: {}", permissionRepository.save(new PermissionEntity(PermissionCode.PAGE_WRITE, true)));
            log.info("Preloading: {}", permissionRepository.save(new PermissionEntity(PermissionCode.BOOK_READ, true)));
            log.info("Preloading: {}", permissionRepository.save(new PermissionEntity(PermissionCode.BOOK_WRITE, true)));

            storageService.deleteAll();
            storageService.init();

        };
    }

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
