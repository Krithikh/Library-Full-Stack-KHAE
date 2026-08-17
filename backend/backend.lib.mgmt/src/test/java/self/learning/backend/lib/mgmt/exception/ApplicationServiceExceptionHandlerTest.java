package self.learning.backend.lib.mgmt.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import self.learning.backend.lib.mgmt.contract.ApplicationCodes;

class ApplicationServiceExceptionHandlerTest {

    private final ApplicationServiceExceptionHandler handler =
            new ApplicationServiceExceptionHandler();

    @ParameterizedTest
    @MethodSource("controlledResponseCodes")
    void shouldMapControlledApplicationConditionToFrozenEnvelope(
            String responseCode,
            String message) {

        var exception = new ApplicationServiceException(
                ApplicationCodes.T01_CREATE_DEPARTMENT,
                responseCode,
                message);

        var response = handler.handleApplicationServiceException(exception);

        assertEquals(ApplicationCodes.T01_CREATE_DEPARTMENT, response.getServiceCode());
        assertEquals(responseCode, response.getResponseCode());
        assertEquals(message, response.getMessage());
        assertNull(response.getData());
    }

    private static Stream<Arguments> controlledResponseCodes() {
        return Stream.of(
                Arguments.of(ApplicationCodes.RESPONSE_INVALID_INPUT, "Invalid input"),
                Arguments.of(ApplicationCodes.RESPONSE_NOT_FOUND, "Not found"),
                Arguments.of(ApplicationCodes.RESPONSE_DUPLICATE, "Duplicate"),
                Arguments.of(ApplicationCodes.RESPONSE_INVALID_STATE, "Invalid state"),
                Arguments.of(ApplicationCodes.RESPONSE_DEPENDENCY_EXISTS, "Dependency exists"));
    }
}
