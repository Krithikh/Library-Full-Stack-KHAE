package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.response.BookReturnResponseDto;

public interface ReadBookReturnService {
    BookReturnResponseDto readBookReturn(Long id);
}
