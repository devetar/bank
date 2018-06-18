package service;

public class SuccResponse {
    private final long id;
    private boolean success;
    private final String content;

    public SuccResponse(long id, boolean success, String content) {
        this.id = id;
        this.success = success;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getContent() {
        return content;
    }
}
