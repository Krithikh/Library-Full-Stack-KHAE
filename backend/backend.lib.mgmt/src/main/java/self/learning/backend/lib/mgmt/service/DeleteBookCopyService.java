package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.response.BookCopyResponseDto;

public interface DeleteBookCopyService {
    BookCopyResponseDto deleteBookCopy(Long id);
}
