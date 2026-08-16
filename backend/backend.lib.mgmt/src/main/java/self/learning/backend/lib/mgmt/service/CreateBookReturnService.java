package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.BookReturnCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.BookReturnResponseDto;

public interface CreateBookReturnService {
    BookReturnResponseDto createBookReturn(BookReturnCreateRequestDto request);
}
