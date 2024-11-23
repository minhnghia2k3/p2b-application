package p2b.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class BookUpdateDto {
    private Long id;

    @JsonProperty("created_by")
    @Nullable
    @Min(1)
    private Long createdBy;

    @Nullable
    @Size(min = 2)
    private String title;

    @Nullable
    @Size(min = 2)
    private String author;

    @JsonProperty("publish_date")
    @Nullable
    private LocalDate publishDate;

    @Nullable
    @Size(min = 2)
    private String source;

    @JsonProperty("page_id")
    @Nullable
    @Min(1)
    private Long pageID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nullable
    public @Min(1) Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(@Nullable @Min(1) Long createdBy) {
        this.createdBy = createdBy;
    }

    @Nullable
    public @Size(min = 2) String getTitle() {
        return title;
    }

    public void setTitle(@Nullable @Size(min = 2) String title) {
        this.title = title;
    }

    @Nullable
    public @Size(min = 2) String getAuthor() {
        return author;
    }

    public void setAuthor(@Nullable @Size(min = 2) String author) {
        this.author = author;
    }

    @Nullable
    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(@Nullable LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    @Nullable
    public @Size(min = 2) String getSource() {
        return source;
    }

    public void setSource(@Nullable @Size(min = 2) String source) {
        this.source = source;
    }

    @Nullable
    public @Min(1) Long getPageID() {
        return pageID;
    }

    public void setPageID(@Nullable @Min(1) Long pageID) {
        this.pageID = pageID;
    }
}
