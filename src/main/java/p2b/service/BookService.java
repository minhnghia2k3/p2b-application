package p2b.service;

import p2b.dto.BookCreateDto;
import p2b.dto.BookUpdateDto;
import p2b.entity.BookEntity;


public interface BookService extends BaseService<BookEntity, BookCreateDto, BookUpdateDto>{
}
