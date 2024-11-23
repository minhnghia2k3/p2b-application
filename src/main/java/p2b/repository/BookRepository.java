package p2b.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import p2b.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    @Query(
            """
                    FROM book b
                    WHERE LOWER(b.title) LIKE LOWER(concat('%', :search, '%'))
                    OR LOWER(b.author) LIKE LOWER(concat('%', :search, '%'))
            """
    )
    Page<BookEntity> findWithFilter(Pageable pageable, String search);
}
