package self.learning.backend.lib.mgmt.exception;

/**
 * Presenter-owned exception used by completed solution services to express a
 * controlled application result without changing the frozen REST contract.
 */
public class ApplicationServiceException extends RuntimeException {

    private final String serviceCode;
    private final String responseCode;

    public ApplicationServiceException(
            String serviceCode,
            String responseCode,
            String message) {
        super(message);
        this.serviceCode = serviceCode;
        this.responseCode = responseCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public String getResponseCode() {
        return responseCode;
    }
}
