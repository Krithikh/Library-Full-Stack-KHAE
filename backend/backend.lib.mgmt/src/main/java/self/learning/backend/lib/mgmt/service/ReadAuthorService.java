package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.response.AuthorResponseDto;

public interface ReadAuthorService {
    AuthorResponseDto readAuthor(Long id);
}
