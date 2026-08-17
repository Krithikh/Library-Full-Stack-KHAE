package self.learning.errorhandlingdemo;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RequestMapping("/rest/demo/books")
public class BookDemoController {

    private final AtomicLong sequence = new AtomicLong(100);
    private final Map<String, BookResponse> books = new ConcurrentHashMap<>();

    public BookDemoController() {
        books.put("ACC-0001", new BookResponse(1L, "Clean Code", "ACC-0001", "Robert C. Martin"));
    }

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
        if (books.containsKey(accessionNumber)) {
            throw new DemoApiException(HttpStatus.CONFLICT, "DUPLICATE_ACCESSION_NUMBER",
                    "Accession number " + accessionNumber + " already exists.", "accessionNumber");
        }
        if ("SERVER ERROR".equalsIgnoreCase(title)) {
            throw new IllegalStateException("Simulated database write failure for classroom demonstration");
        }
        if ("SLOW BOOK".equalsIgnoreCase(title)) {
            Thread.sleep(3000);
        }

        long id = sequence.incrementAndGet();
        BookResponse response = new BookResponse(id, title, accessionNumber, author);
        books.put(accessionNumber, response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim();
    }

    public record BookRequest(String title, String accessionNumber, String author) {}
    public record BookResponse(Long id, String title, String accessionNumber, String author) {}
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
                ex.status().value(), ex.code(), ex.getMessage(), ex.field(), OffsetDateTime.now().toString()));
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiErrorResponse> handleUnexpected(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiErrorResponse(
                500, "SERVER_ERROR", "The Library server could not complete the request.", null,
                OffsetDateTime.now().toString()));
    }

    record ApiErrorResponse(int status, String code, String message, String field, String timestamp) {}
}
