package p2b.dto;

public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;
    private int count;
    private int currentPage;
    private int totalPages;

    public ApiResponse() {
    }

    public ApiResponse(int code, String message, T data, int count, int currentPage, int totalPages) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.count = count;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
