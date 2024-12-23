package p2b.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {

    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Resource load(String filename);

    void deleteAll();
}
