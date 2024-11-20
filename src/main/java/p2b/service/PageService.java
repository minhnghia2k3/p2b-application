package p2b.service;

import org.springframework.data.domain.Page;
import p2b.dto.PageCreateDto;
import p2b.dto.PageUpdateDto;
import p2b.entity.PageEntity;


public interface PageService {
    Page<PageEntity> listUserPages(int page, int limit, String sort, String search);
    PageEntity getUserPage(long id);
    PageEntity createPage(PageCreateDto page);
    PageEntity updatePage(long pageId, PageUpdateDto page);
    void deletePage(long id);
}
