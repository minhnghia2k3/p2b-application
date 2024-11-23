package p2b.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import p2b.dto.ApiResponse;

import java.util.List;


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

    public static <T> ApiResponse<List<T>> prepareResponse(Page<T> list) {
        var response = new ApiResponse<List<T>>();

        response.setCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        response.setCurrentPage(list.getNumber() + 1);
        response.setCount(list.getNumberOfElements());
        response.setTotalPages(list.getTotalPages());
        response.setData(list.getContent());

        return response;
    }
}
