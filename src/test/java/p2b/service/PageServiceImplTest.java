package p2b.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import p2b.DTO.PageCreateDto;
import p2b.DTO.PageUpdateDto;
import p2b.entity.PageEntity;
import p2b.entity.Status;
import p2b.exception.PageNotFoundException;
import p2b.repository.PageRepository;
import p2b.utils.PageableUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PageServiceImplTest {
    // mock repository
    @Mock
    private PageRepository pageRepository;

    @InjectMocks
    private PageServiceImpl pageService;

    /* List page */
    @Test
    void givenDefaultParam_whenGetAllPages_thenReturnPages() {
        // 1. create mock data
        List<PageEntity> pages = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            var newPage = new PageEntity(1, "test title " + i, Status.PRIVATE, "test page content");
            pages.add(newPage);
        }

        // 2. define findAll behavior
        Pageable paging = PageableUtil.createPageRequest(0, 10, "id");
        Page<PageEntity> mockPage = new PageImpl<>(pages, paging, pages.size());
        when(pageRepository.findAll(paging)).thenReturn(mockPage);

        // 3. create service
        var got = pageService.listUserPages(0, 10, "id", "");

        // 4. assert the result
        assertThat(got.getContent()).isNotNull();
        assertThat(got.getContent()).isEqualTo(pages);

        // 5. verify mocked behavior
        verify(pageRepository).findAll(paging);
    }

    /* Get Page */
    @Test
    void givenPageId_whenPageIsExists_ThenReturnPage() {
        // 1. Create mock data
        var newPage = new PageEntity(2, "test title", Status.PRIVATE, "test page content");
        // 2. define behavior of repository
        when(pageRepository.findById(1L)).thenReturn(Optional.of(newPage));

        // 3. create service
        var foundPage = pageService.getUserPage(1L);

        // 4. assert the result
        assertThat(foundPage).isEqualTo(newPage);

        // 4.1 ensure repository is called
        verify(pageRepository).findById(1L);
    }

    @Test
    void givenPageId_whenPageIsNotExists_ThenReturnNull() {
        when(pageRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> pageService.getUserPage(1L))
                .isInstanceOf(PageNotFoundException.class);

        verify(pageRepository).findById(1L);
    }

    /* Create page */
    @Test
    void givenValidDto_whenCreatePage_thenReturnPage() {
        // Arrange: Set up the expected page entity
        var page = new PageEntity(1, "test title", Status.PRIVATE, "test page content");

        // Create a DTO that will be passed to the service method
        var dto = new PageCreateDto(1, "test title", Status.PRIVATE, "test page content");

        // Mock the behavior of the repository save method
        when(pageRepository.save(any(PageEntity.class))).thenReturn(page);  // Use 'any(PageEntity.class)' to match any PageEntity

        // Act: Call the service method to create the page
        var newPage = pageService.createPage(dto);

        // Assert: Verify that the returned page is equal to the expected page
        assertThat(newPage).isEqualTo(page);

        // Verify that the save method was called with the correct entity (i.e., newPage)
        verify(pageRepository).save(any(PageEntity.class));
    }


    /* Update page */
    @Test
    void givenValidDto_whenUpdatePage_thenReturnPage() {
        var oldPage = new PageEntity(1, "test title", Status.PRIVATE, "test page content");

        var page = new PageEntity(1, "test title updated", Status.PRIVATE, "test page content updated");

        when(pageRepository.findById(1L)).thenReturn(Optional.of(oldPage));

        when(pageRepository.save(any(PageEntity.class))).thenReturn(page);

        var dto = new PageUpdateDto("test title updated", Status.PRIVATE, "test page content updated");

        var updatedPage = pageService.updatePage(1, dto);

        assertThat(updatedPage).isEqualTo(page);

        verify(pageRepository).findById(1L);

        verify(pageRepository).save(any(PageEntity.class));
    }

    /* Delete page */
    @Test
    void givenValidPageId_whenDeletePage_thenSuccess() {
        // 1. Arrange
        var page = new PageEntity(1, 1L, "test title", Status.PRIVATE, "test page content");
        when(pageRepository.findById(1L)).thenReturn(Optional.of(page));

        pageService.deletePage(1L);

        // 3. Assert and Verify
        verify(pageRepository).findById(1L);   // Ensure the ID was checked
        verify(pageRepository).deleteById(1L); // Ensure the delete method was called
    }
}
