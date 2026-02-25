package dto.response;

public class ApiResponse {
    private boolean success;
    private String message;

    public ApiResponse() {}

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static ApiResponse ok(String msg) { return new ApiResponse(true, msg); }
    public static ApiResponse fail(String msg) { return new ApiResponse(false, msg); }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
}