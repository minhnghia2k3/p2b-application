package p2b.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "page")
public class PageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long owner;

    private String title;

    private Status status;

    @Column(name = "page_content")
    private String pageContent;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "page")
    private Set<BookEntity> books;

    /* for testing */
    public PageEntity(long id, long owner, String title, Status status, String pageContent) {
        this(owner, title, status, pageContent);
        this.id = id;
    }

    public PageEntity(long owner, String title, Status status, String pageContent) {
        this.owner = owner;
        this.title = title;
        this.status = status;
        this.pageContent = pageContent;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public PageEntity() {

    }

    public long getId() {
        return id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Page{" + "id=" + id + ", owner=" + owner + ", title='" + title + '\'' + ", status=" + status + ", pageContent='" + pageContent + '\'' + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
}
