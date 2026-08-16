package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.response.BookCopyResponseDto;

public interface ReadBookCopyService {
    BookCopyResponseDto readBookCopy(Long id);
}
