    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.AuthorResponseDto;
import self.learning.backend.lib.mgmt.service.UpdateAuthorService;
import self.learning.backend.lib.mgmt.dto.request.AuthorUpdateRequestDto;

    @Service
    public class UpdateAuthorServiceImpl implements UpdateAuthorService {
        @Override
    public AuthorResponseDto updateAuthor(Long id, AuthorUpdateRequestDto request) {
        AuthorResponseDto response = new AuthorResponseDto();
        response.setAuthorId(id);
        response.setAuthorCode(request.getAuthorCode());
        response.setAuthorName(request.getAuthorName());
        response.setActive(true);
        return response;
    }
    }
