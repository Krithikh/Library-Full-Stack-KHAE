package self.learning.backend.lib.mgmt.dto;

public class ApiResponse<T> {
    private String serviceCode;
    private String responseCode;
    private String message;
    private T data;

    public String getServiceCode() { return serviceCode; }
    public void setServiceCode(String serviceCode) { this.serviceCode = serviceCode; }
    public String getResponseCode() { return responseCode; }
    public void setResponseCode(String responseCode) { this.responseCode = responseCode; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public static <T> ApiResponse<T> success(String serviceCode, String message, T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setServiceCode(serviceCode);
        response.setResponseCode("00");
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
