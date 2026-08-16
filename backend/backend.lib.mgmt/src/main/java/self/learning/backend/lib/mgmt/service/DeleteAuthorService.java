package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.response.AuthorResponseDto;

public interface DeleteAuthorService {
    AuthorResponseDto deleteAuthor(Long id);
}
