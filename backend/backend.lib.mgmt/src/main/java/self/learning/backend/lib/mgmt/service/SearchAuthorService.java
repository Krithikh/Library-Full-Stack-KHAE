package self.learning.backend.lib.mgmt.service;

import java.util.List;
import self.learning.backend.lib.mgmt.dto.response.AuthorResponseDto;

public interface SearchAuthorService {
    List<AuthorResponseDto> searchAuthor(String text);
}
