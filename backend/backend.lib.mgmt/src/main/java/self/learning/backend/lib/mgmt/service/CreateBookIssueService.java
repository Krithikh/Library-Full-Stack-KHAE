package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.BookIssueCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.BookIssueResponseDto;

public interface CreateBookIssueService {
    BookIssueResponseDto createBookIssue(BookIssueCreateRequestDto request);
}
