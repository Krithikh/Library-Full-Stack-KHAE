package self.learning.backend.lib.mgmt.service;

import java.util.List;
import self.learning.backend.lib.mgmt.dto.response.BookResponseDto;

public interface SearchBookService {
    List<BookResponseDto> searchBook(String text);
}
