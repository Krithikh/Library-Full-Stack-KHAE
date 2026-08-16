package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.AuthorCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.AuthorResponseDto;

public interface CreateAuthorService {
    AuthorResponseDto createAuthor(AuthorCreateRequestDto request);
}
