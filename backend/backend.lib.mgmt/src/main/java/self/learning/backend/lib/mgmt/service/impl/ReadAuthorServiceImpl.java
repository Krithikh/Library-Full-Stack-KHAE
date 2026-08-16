    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.AuthorResponseDto;
import self.learning.backend.lib.mgmt.service.ReadAuthorService;

    @Service
    public class ReadAuthorServiceImpl implements ReadAuthorService {
        @Override
    public AuthorResponseDto readAuthor(Long id) {
        AuthorResponseDto response = new AuthorResponseDto();
        response.setAuthorId(2L);
        response.setAuthorCode("AUT-002");
        response.setAuthorName("Joshua Bloch");
        response.setActive(true);
        response.setAuthorId(id);
        return response;
    }
    }
