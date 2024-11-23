package p2b.entity;

public class FileInfo {
    private String name;
    private String url;
    private String code;

    public FileInfo(String name, String url, String  code) {
        this.name = name;
        this.url = url;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
