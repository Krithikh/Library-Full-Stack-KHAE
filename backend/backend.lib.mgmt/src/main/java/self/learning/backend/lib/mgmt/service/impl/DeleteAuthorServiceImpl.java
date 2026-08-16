    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.AuthorResponseDto;
import self.learning.backend.lib.mgmt.service.DeleteAuthorService;

    @Service
    public class DeleteAuthorServiceImpl implements DeleteAuthorService {
        @Override
    public AuthorResponseDto deleteAuthor(Long id) {
        AuthorResponseDto response = new AuthorResponseDto();
        response.setAuthorId(2L);
        response.setAuthorCode("AUT-002");
        response.setAuthorName("Joshua Bloch");
        response.setActive(true);
        response.setAuthorId(id);
        response.setActive(false);
        return response;
    }
    }
