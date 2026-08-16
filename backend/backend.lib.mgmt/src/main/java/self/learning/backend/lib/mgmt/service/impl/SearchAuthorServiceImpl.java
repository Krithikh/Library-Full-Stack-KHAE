    package self.learning.backend.lib.mgmt.service.impl;

    import java.util.List;
import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.AuthorResponseDto;
import self.learning.backend.lib.mgmt.service.SearchAuthorService;

    @Service
    public class SearchAuthorServiceImpl implements SearchAuthorService {
        @Override
    public List<AuthorResponseDto> searchAuthor(String text) {
        AuthorResponseDto response = new AuthorResponseDto();
        response.setAuthorId(2L);
        response.setAuthorCode("AUT-002");
        response.setAuthorName("Joshua Bloch");
        response.setActive(true);
        return List.of(response);
    }
    }
