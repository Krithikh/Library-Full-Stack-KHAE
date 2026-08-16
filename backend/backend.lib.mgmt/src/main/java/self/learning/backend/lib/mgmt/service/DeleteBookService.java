package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.response.BookResponseDto;

public interface DeleteBookService {
    BookResponseDto deleteBook(Long id);
}
