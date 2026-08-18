package self.learning.errorhandlingdemo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class BookDemoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldCreateBookFromStub() throws Exception {
        mockMvc.perform(post("/rest/demo/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Refactoring\",\"accessionNumber\":\"ACC-9001\",\"author\":\"Martin Fowler\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title").value("Refactoring"))
            .andExpect(jsonPath("$.source").value("STUB_ONLY"));
    }

    @Test
    void shouldReturnFieldValidationError() throws Exception {
        mockMvc.perform(post("/rest/demo/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"\",\"accessionNumber\":\"ACC-9002\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"))
            .andExpect(jsonPath("$.field").value("title"));
    }

    @Test
    void shouldReturnBackendFormatValidationError() throws Exception {
        mockMvc.perform(post("/rest/demo/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"DDD\",\"accessionNumber\":\"BAD-1\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("INVALID_ACCESSION_FORMAT"))
            .andExpect(jsonPath("$.field").value("accessionNumber"));
    }

    @Test
    void shouldReturnDuplicateConflict() throws Exception {
        mockMvc.perform(post("/rest/demo/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Another Book\",\"accessionNumber\":\"ACC-0001\"}"))
            .andExpect(status().isConflict())
            .andExpect(jsonPath("$.code").value("DUPLICATE_ACCESSION_NUMBER"));
    }

    @Test
    void shouldReturn401Stub() throws Exception {
        mockMvc.perform(post("/rest/demo/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"SESSION EXPIRED\",\"accessionNumber\":\"ACC-4010\"}"))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value("SESSION_EXPIRED"));
    }

    @Test
    void shouldReturn403Stub() throws Exception {
        mockMvc.perform(post("/rest/demo/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"FORBIDDEN BOOK\",\"accessionNumber\":\"ACC-4030\"}"))
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value("FORBIDDEN_OPERATION"));
    }

    @Test
    void shouldReturn404Stub() throws Exception {
        mockMvc.perform(post("/rest/demo/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"MISSING BOOK\",\"accessionNumber\":\"ACC-4040\"}"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value("RELATED_RECORD_NOT_FOUND"));
    }

    @Test
    void shouldHideTechnicalServerException() throws Exception {
        mockMvc.perform(post("/rest/demo/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"SERVER ERROR\",\"accessionNumber\":\"ACC-9003\"}"))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.code").value("SERVER_ERROR"))
            .andExpect(jsonPath("$.message").value("The Library server could not complete the request."))
            .andExpect(jsonPath("$.source").value("STUB_ONLY"));
    }
}
