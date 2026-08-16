package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.AuthorUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.AuthorResponseDto;

public interface UpdateAuthorService {
    AuthorResponseDto updateAuthor(Long id, AuthorUpdateRequestDto request);
}
