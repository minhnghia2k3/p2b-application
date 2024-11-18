package p2b.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import p2b.DTO.PageCreateDto;
import p2b.DTO.PageUpdateDto;
import p2b.entity.PageEntity;
import p2b.exception.PageNotFoundException;
import p2b.repository.PageRepository;
import p2b.utils.PageableUtil;

import java.time.LocalDateTime;

@Service
public class PageServiceImpl implements PageService {
    // Inject repository
    private final PageRepository pageRepository;

    @Autowired
    public PageServiceImpl(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @Override
    public Page<PageEntity> listUserPages(int pageNumber, int pageSize, String sort, String search) {
        Pageable paging = PageableUtil.createPageRequest(pageNumber, pageSize, sort);

        if (search != null && !search.isEmpty()) {
            return pageRepository.findWithFilter(search, paging);
        }

        return pageRepository.findAll(paging);
    }

    @Override
    public PageEntity getUserPage(long id) {
        var page = pageRepository.findById(id);

        if (page.isEmpty()) {
            throw new PageNotFoundException("not found page - " + id);
        }

        return page.get();
    }

    @Override
    @Transactional
    public PageEntity createPage(PageCreateDto page) {
        PageEntity newPage = new PageEntity(page.getOwner(), page.getTitle(), page.getStatus(), page.getPageContent());
        return pageRepository.save(newPage);
    }

    @Override
    @Transactional
    public PageEntity updatePage(long pageId, PageUpdateDto page) {
        var p = getUserPage(pageId);

        if (page.getTitle() != null) {
            p.setTitle(page.getTitle());
        }

        if (page.getStatus() != null) {
            p.setStatus(page.getStatus());
        }

        if (page.getPageContent() != null) {
            p.setPageContent(page.getPageContent());
        }

        p.setUpdatedAt(LocalDateTime.now());

        return pageRepository.save(p);
    }

    @Override
    @Transactional
    public void deletePage(long id) {
        var page = getUserPage(id);
        pageRepository.deleteById(page.getId());
    }
}
