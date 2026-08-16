package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.BookCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.BookResponseDto;

public interface CreateBookService {
    BookResponseDto createBook(BookCreateRequestDto request);
}
