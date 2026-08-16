package self.learning.backend.lib.mgmt.service;

import java.util.List;
import self.learning.backend.lib.mgmt.dto.response.BookReturnResponseDto;

public interface SearchBookReturnService {
    List<BookReturnResponseDto> searchBookReturn(String text);
}
