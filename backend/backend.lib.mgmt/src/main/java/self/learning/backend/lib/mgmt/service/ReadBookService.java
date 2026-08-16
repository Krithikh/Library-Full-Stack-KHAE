package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.response.BookResponseDto;

public interface ReadBookService {
    BookResponseDto readBook(Long id);
}
