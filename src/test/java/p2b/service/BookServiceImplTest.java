package p2b.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import p2b.entity.BookEntity;
import p2b.entity.PageEntity;
import p2b.entity.Status;
import p2b.repository.BookRepository;
import p2b.utils.PageableUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;



    //TODO: test case with search
    @Test
    void givenDefaultParams_whenGetAllBooks_returnsPageOfBooks() {
        // 1. create mock data
        PageEntity page = new PageEntity(1, 1, "First page", Status.PRIVATE, "page content");

        List<BookEntity> books = new ArrayList<>(
                List.of(
                        new BookEntity(1,  "The first book", "The first author", LocalDate.now(), "https://google.com", page),
                        new BookEntity(2, "The second book", "The first author", LocalDate.now(), "https://google.com", page),
                        new BookEntity(3, "The third book", "The first author", LocalDate.now(), "https://google.com", page)
                )
        );

        // 2. mock findAll repo behavior
        Pageable paging = PageableUtil.createPageRequest(0, 5, "-id");
        Page<BookEntity> mockBooks = new PageImpl<>(books, paging, books.size());
        when(bookRepository.findAll(paging)).thenReturn(mockBooks);
        // 3. create service
        var got = bookService.findAll(1, 5, "id", "");

        // assert result
        assertThat(got.getContent()).isNotNull();
        assertThat(got.getContent()).isEqualTo(books);

        // verify mocked behavior
        verify(bookRepository).findWithFilter(paging, "");
    }
}
