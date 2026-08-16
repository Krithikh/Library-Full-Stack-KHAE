package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.BookUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.BookResponseDto;

public interface UpdateBookService {
    BookResponseDto updateBook(Long id, BookUpdateRequestDto request);
}
