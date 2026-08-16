package self.learning.backend.lib.mgmt.service;

import java.util.List;
import self.learning.backend.lib.mgmt.dto.response.BookCopyResponseDto;

public interface SearchBookCopyService {
    List<BookCopyResponseDto> searchBookCopy(String text);
}
