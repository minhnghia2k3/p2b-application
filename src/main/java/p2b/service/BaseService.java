package p2b.service;

import org.springframework.data.domain.Page;

public interface BaseService<T, S, U> {
    default Page<T> findAll(int page, int limit, String sort, String search){
        System.out.printf("Class %s is not implemented yet", this.getClass().getSimpleName());
        return null;
    }

    T findById(long id);

    T insert(S s);

    T update(U u);

    void delete(long id);
}
