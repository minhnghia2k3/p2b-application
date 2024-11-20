package p2b.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import p2b.entity.Status;

public class PageCreateDto {
    @NotNull()
    @Min(1)
    private long owner;

    @NotNull()
    @Size(min = 2, max = 255)
    private String title;

    @NotNull()
    private Status status;

    @JsonProperty("page_content")
    private String pageContent;

    public PageCreateDto(long owner, String title, Status status, String pageContent) {
        this.owner = owner;
        this.title = title;
        this.status = status;
        this.pageContent = pageContent;
    }

    public long getOwner() {
        return owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
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
