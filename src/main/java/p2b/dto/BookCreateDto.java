package p2b.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class BookCreateDto {
    @JsonProperty("created_by")
    @NotNull
    @Min(1)
    private long createdBy;

    @NotBlank
    @Size(min = 2)
    private String title;

    @NotBlank
    @Size(min = 2)
    private String author;

    @JsonProperty("publish_date")
    private LocalDate publishDate;

    private String source;

    @JsonProperty("page_id")
    @Min(1)
    private long pageID;

    @NotNull
    @Min(1)
    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(@NotNull @Min(1) long createdBy) {
        this.createdBy = createdBy;
    }

    public @NotBlank @Size(min = 2) String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank @Size(min = 2) String title) {
        this.title = title;
    }

    public @NotBlank @Size(min = 2) String getAuthor() {
        return author;
    }

    public void setAuthor(@NotBlank @Size(min = 2) String author) {
        this.author = author;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Min(1)
    public long getPageID() {
        return pageID;
    }

    public void setPageID(@Min(1) long pageID) {
        this.pageID = pageID;
    }
}
