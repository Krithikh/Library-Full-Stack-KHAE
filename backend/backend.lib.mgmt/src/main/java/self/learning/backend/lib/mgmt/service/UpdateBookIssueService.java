package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.BookIssueUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.BookIssueResponseDto;

public interface UpdateBookIssueService {
    BookIssueResponseDto updateBookIssue(Long id, BookIssueUpdateRequestDto request);
}
