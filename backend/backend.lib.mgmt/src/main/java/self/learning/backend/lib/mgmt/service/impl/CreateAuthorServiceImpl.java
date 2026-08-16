    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.AuthorResponseDto;
import self.learning.backend.lib.mgmt.service.CreateAuthorService;
import self.learning.backend.lib.mgmt.dto.request.AuthorCreateRequestDto;

    @Service
    public class CreateAuthorServiceImpl implements CreateAuthorService {
        @Override
    public AuthorResponseDto createAuthor(AuthorCreateRequestDto request) {
        AuthorResponseDto response = new AuthorResponseDto();
        response.setAuthorId(1001L);
        response.setAuthorCode(request.getAuthorCode());
        response.setAuthorName(request.getAuthorName());
        response.setActive(true);
        return response;
    }
    }
