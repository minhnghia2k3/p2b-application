package p2b.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class PageableUtil {

    public static Pageable createPageRequest(int page, int pageSize, String sort) {
        Pageable paging;

        if (sort.startsWith("-")) {
            sort = sort.substring(1);
            paging = PageRequest.of(page, pageSize, Sort.by(sort).descending());
        } else {
            paging = PageRequest.of(page, pageSize, Sort.by(sort));
        }

        return paging;
    }
}
