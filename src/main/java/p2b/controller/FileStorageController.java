package p2b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import p2b.dto.ApiResponse;
import p2b.entity.FileInfo;
import p2b.service.FileStorageService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/uploads")
public class FileStorageController {
    FileStorageService storageService;

    @Autowired
    public FileStorageController(FileStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<?>> uploadFile(@RequestParam("file") MultipartFile file) {
        storageService.store(file);

        return ResponseEntity.ok(new ApiResponse<>(
                HttpStatus.OK.value(),
                "Uploaded the file successfully! " + file.getOriginalFilename(),
                null));
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll()
                .map(path -> {
                    String filename = path.getFileName().toString();
                    String url = MvcUriComponentsBuilder.
                            fromMethodName(FileStorageController.class,
                                    "getFile",
                                    filename).build().toString();
                    return new FileInfo(filename, url, null);
                }).collect(Collectors.toList());

        return ResponseEntity.ok(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
