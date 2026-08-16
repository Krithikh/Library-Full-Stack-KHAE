package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.BookCopyCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.BookCopyResponseDto;

public interface CreateBookCopyService {
    BookCopyResponseDto createBookCopy(BookCopyCreateRequestDto request);
}
