package self.learning.backend.lib.mgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import self.learning.backend.lib.mgmt.dto.ApiResponse;

/**
 * Presenter-owned REST adapter for known application conditions.
 * Controlled application results intentionally keep HTTP 200 and use the
 * frozen serviceCode/responseCode/message envelope.
 */
@RestControllerAdvice(basePackages = "self.learning.backend.lib.mgmt.controller.rest")
public class ApplicationServiceExceptionHandler {

    @ExceptionHandler(ApplicationServiceException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Object> handleApplicationServiceException(
            ApplicationServiceException exception) {

        ApiResponse<Object> response = new ApiResponse<>();
        response.setServiceCode(exception.getServiceCode());
        response.setResponseCode(exception.getResponseCode());
        response.setMessage(exception.getMessage());
        response.setData(null);
        return response;
    }
}
