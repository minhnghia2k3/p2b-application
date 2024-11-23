package p2b.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import p2b.dto.ApiResponse;
import p2b.dto.BookCreateDto;
import p2b.dto.BookUpdateDto;
import p2b.entity.BookEntity;
import p2b.service.BookService;
import p2b.utils.PageableUtil;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    // Inject service
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<BookEntity>>> getAllBooks(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "createdAt") String sort,
            @RequestParam(defaultValue = "") String search
    ) {
        var books = bookService.findAll(page, pageSize, sort, search);

        var response = PageableUtil.prepareResponse(books);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{bookID}")
    public ResponseEntity<ApiResponse<BookEntity>> getBook(@PathVariable long bookID) {
        var response = new ApiResponse<>(HttpStatus.OK.value(), bookService.findById(bookID));
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<BookEntity>> createBook(@Valid @RequestBody BookCreateDto dto) {
        var response = new ApiResponse<>(HttpStatus.CREATED.value(), bookService.insert(dto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{bookID}")
    public ResponseEntity<ApiResponse<BookEntity>> updateBook(@PathVariable long bookID, @Valid @RequestBody BookUpdateDto dto) {
        dto.setId(bookID);
        var response = new ApiResponse<>(HttpStatus.OK.value(), bookService.update(dto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{bookID}")
    public ResponseEntity<Object> deleteBook(@PathVariable long bookID) {
        bookService.delete(bookID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
