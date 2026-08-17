package self.learning.errorhandlingdemo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class BookDemoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldCreateBook() throws Exception {
        mockMvc.perform(post("/rest/demo/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Refactoring\",\"accessionNumber\":\"ACC-9001\",\"author\":\"Martin Fowler\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title").value("Refactoring"));
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
    void shouldReturnDuplicateConflict() throws Exception {
        mockMvc.perform(post("/rest/demo/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Another Book\",\"accessionNumber\":\"ACC-0001\"}"))
            .andExpect(status().isConflict())
            .andExpect(jsonPath("$.code").value("DUPLICATE_ACCESSION_NUMBER"));
    }

    @Test
    void shouldHideTechnicalServerException() throws Exception {
        mockMvc.perform(post("/rest/demo/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"SERVER ERROR\",\"accessionNumber\":\"ACC-9003\"}"))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.code").value("SERVER_ERROR"))
            .andExpect(jsonPath("$.message").value("The Library server could not complete the request."));
    }
}
