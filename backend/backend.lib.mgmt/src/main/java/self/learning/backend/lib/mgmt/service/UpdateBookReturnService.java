package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.BookReturnUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.BookReturnResponseDto;

public interface UpdateBookReturnService {
    BookReturnResponseDto updateBookReturn(Long id, BookReturnUpdateRequestDto request);
}
