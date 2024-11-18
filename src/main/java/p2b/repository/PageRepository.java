package p2b.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import p2b.entity.PageEntity;


public interface PageRepository extends JpaRepository<PageEntity, Long> {
    // lowercase field and value then compare it
    @Query("FROM page p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(p.pageContent) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<PageEntity> findWithFilter(@Param("search") String search, Pageable pageable);
}
