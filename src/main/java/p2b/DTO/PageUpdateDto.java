package p2b.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import p2b.entity.Status;

public class PageUpdateDto {
    @Size(min = 2, max = 255)
    private String title;

    private Status status;

    @JsonProperty("page_content")
    private String pageContent;

    public PageUpdateDto(String title, Status status, String pageContent) {
        this.title = title;
        this.status = status;
        this.pageContent = pageContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }
}
