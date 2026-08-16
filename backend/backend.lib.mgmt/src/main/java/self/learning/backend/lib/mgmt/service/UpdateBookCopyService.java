package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.BookCopyUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.BookCopyResponseDto;

public interface UpdateBookCopyService {
    BookCopyResponseDto updateBookCopy(Long id, BookCopyUpdateRequestDto request);
}
