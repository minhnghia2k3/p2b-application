package p2b.service;

import org.springframework.data.domain.Page;

public interface BaseService<T, S, U> {
    Page<T> findAll(int page, int limit, String sort, String search);

    T findById(long id);

    T insert(S s);

    T update(U u);

    void delete(long id);
}
