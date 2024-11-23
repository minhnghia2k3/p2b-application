package p2b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import p2b.dto.BookCreateDto;
import p2b.dto.BookUpdateDto;
import p2b.entity.BookEntity;
import p2b.entity.PageEntity;
import p2b.exception.BookNotFoundException;
import p2b.repository.BookRepository;
import p2b.utils.PageableUtil;

import java.time.LocalDateTime;

@Service
public class BookServiceImpl implements BookService {
    // Inject repository
    private final BookRepository bookRepository;
    private final PageService pageService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, PageService pageService) {
        this.bookRepository = bookRepository;
        this.pageService = pageService;
    }


    @Override
    public Page<BookEntity> findAll(int page, int limit, String sort, String search) {
        if (page > 0) {
            page -= 1;
        }

        Pageable paging = PageableUtil.createPageRequest(page, limit, sort);

        if (search != null && !search.isEmpty()) {
            return bookRepository.findWithFilter(paging, search);
        }

        return bookRepository.findAll(paging);
    }

    @Override
    public BookEntity findById(long id) {
        var founded = bookRepository.findById(id);

        if (founded.isEmpty()) {
            throw new BookNotFoundException(id);
        }

        return founded.get();
    }

    @Override
    public BookEntity insert(BookCreateDto dto) {
        // TODO: validate created_by
        // Validate page
        PageEntity page = pageService.getUserPage(dto.getPageID());

        BookEntity book = new BookEntity(
                dto.getCreatedBy(),
                dto.getTitle(),
                dto.getAuthor(),
                dto.getPublishDate(),
                dto.getSource(),
                page
        );

        return bookRepository.save(book);
    }

    @Override
    public BookEntity update(BookUpdateDto dto) {
        // Validate book id
        BookEntity book = findById(dto.getId());

        // partial update book entity

        if (dto.getAuthor() != null) {
            book.setAuthor(dto.getAuthor());
        }

        if (dto.getTitle() != null) {
            book.setTitle(dto.getTitle());
        }

        if (dto.getPublishDate() != null) {
            book.setPublishDate(dto.getPublishDate());
        }

        if (dto.getSource() != null) {
            book.setSource(dto.getSource());
        }

        if (dto.getCreatedBy() > 0) {
            book.setCreatedBy(dto.getCreatedBy());
        }

        if (dto.getPageID() > 0) {
            // Validate page id
            PageEntity page = pageService.getUserPage(dto.getPageID());

            book.setPage(page);
        }

        book.setUpdatedAt(LocalDateTime.now());

        return bookRepository.save(book);
    }

    @Override
    public void delete(long id) {
        // Validate book id
        BookEntity book = findById(id);

        bookRepository.delete(book);
    }
}
