package p2b.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import p2b.dto.ApiResponse;
import p2b.dto.PageCreateDto;
import p2b.dto.PageUpdateDto;
import p2b.entity.PageEntity;
import p2b.service.PageService;
import p2b.utils.PageableUtil;

import java.util.List;

@RestController
@RequestMapping("api/v1/pages")
public class PageController {

    //TODO: add middleware to validate user logged in

    // inject service
    private final PageService pageService;

    @Autowired
    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<PageEntity>>> listPages(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "-createdAt") String sort,
            @RequestParam(defaultValue = "") String search
    ) {

        Page<PageEntity> list = pageService.listUserPages(page, pageSize, sort, search);

        var response = PageableUtil.prepareResponse(list);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{pageId}")
    public ResponseEntity<PageEntity> getPage(@PathVariable long pageId) {
        var page = pageService.getUserPage(pageId);
        return new ResponseEntity<>(page, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<PageEntity> createPage(@Valid @RequestBody PageCreateDto page) {
        return new ResponseEntity<>(pageService.createPage(page), new HttpHeaders(), HttpStatus.CREATED);
    }

    @PatchMapping("/{pageId}")
    public ResponseEntity<PageEntity> updatePage(@PathVariable long pageId, @Valid @RequestBody PageUpdateDto page) {
        return new ResponseEntity<>(pageService.updatePage(pageId, page), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{pageId}")
    public ResponseEntity<Object> deletePage(@PathVariable long pageId) {
        pageService.deletePage(pageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
