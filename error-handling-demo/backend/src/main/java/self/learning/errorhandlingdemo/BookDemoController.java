package self.learning.errorhandlingdemo;

import java.time.OffsetDateTime;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Classroom-only stub API.
 *
 * There is deliberately no database, repository, JPA entity, JDBC connection,
 * datasource, or persistence layer in this demo. Responses are deterministic
 * and are selected from the submitted values so the presenter can reproduce
 * the same error conditions every time.
 */
@RestController
@RequestMapping("/rest/demo/books")
public class BookDemoController {

    private final AtomicLong sequence = new AtomicLong(1000);

    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody BookRequest request) throws InterruptedException {
        String title = normalize(request.title());
        String accessionNumber = normalize(request.accessionNumber()).toUpperCase();
        String author = normalize(request.author());

        if (title.isBlank()) {
            throw new DemoApiException(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR",
                    "Book title is required.", "title");
        }
        if (accessionNumber.isBlank()) {
            throw new DemoApiException(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR",
                    "Accession number is required.", "accessionNumber");
        }
        if (!accessionNumber.matches("ACC-[0-9]{4}")) {
            throw new DemoApiException(HttpStatus.BAD_REQUEST, "INVALID_ACCESSION_FORMAT",
                    "Accession number must use the format ACC-0000.", "accessionNumber");
        }
        if ("ACC-0001".equals(accessionNumber)) {
            throw new DemoApiException(HttpStatus.CONFLICT, "DUPLICATE_ACCESSION_NUMBER",
                    "Accession number ACC-0001 already exists in the teaching stub.", "accessionNumber");
        }
        if ("SESSION EXPIRED".equalsIgnoreCase(title)) {
            throw new DemoApiException(HttpStatus.UNAUTHORIZED, "SESSION_EXPIRED",
                    "Your Library session has expired. Please sign in again.", null);
        }
        if ("FORBIDDEN BOOK".equalsIgnoreCase(title)) {
            throw new DemoApiException(HttpStatus.FORBIDDEN, "FORBIDDEN_OPERATION",
                    "You do not have permission to perform this Library operation.", null);
        }
        if ("MISSING BOOK".equalsIgnoreCase(title)) {
            throw new DemoApiException(HttpStatus.NOT_FOUND, "RELATED_RECORD_NOT_FOUND",
                    "The requested Library record was not found.", null);
        }
        if ("SERVER ERROR".equalsIgnoreCase(title)) {
            throw new IllegalStateException("Simulated backend processing failure for classroom demonstration");
        }
        if ("SLOW BOOK".equalsIgnoreCase(title)) {
            Thread.sleep(3000);
        }

        long id = sequence.incrementAndGet();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BookResponse(id, title, accessionNumber, author, "STUB_ONLY"));
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim();
    }

    public record BookRequest(String title, String accessionNumber, String author) {}
    public record BookResponse(Long id, String title, String accessionNumber, String author, String source) {}
}

class DemoApiException extends RuntimeException {
    private final HttpStatus status;
    private final String code;
    private final String field;

    DemoApiException(HttpStatus status, String code, String message, String field) {
        super(message);
        this.status = status;
        this.code = code;
        this.field = field;
    }

    HttpStatus status() { return status; }
    String code() { return code; }
    String field() { return field; }
}

@RestControllerAdvice
class DemoApiExceptionHandler {

    @ExceptionHandler(DemoApiException.class)
    ResponseEntity<ApiErrorResponse> handleDemo(DemoApiException ex) {
        return ResponseEntity.status(ex.status()).body(new ApiErrorResponse(
                ex.status().value(), ex.code(), ex.getMessage(), ex.field(),
                "/rest/demo/books", OffsetDateTime.now().toString(), "STUB_ONLY"));
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiErrorResponse> handleUnexpected(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiErrorResponse(
                500, "SERVER_ERROR", "The Library server could not complete the request.", null,
                "/rest/demo/books", OffsetDateTime.now().toString(), "STUB_ONLY"));
    }

    record ApiErrorResponse(int status, String code, String message, String field,
                            String path, String timestamp, String source) {}
}
