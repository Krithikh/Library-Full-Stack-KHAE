package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.response.BookIssueResponseDto;

public interface ReadBookIssueService {
    BookIssueResponseDto readBookIssue(Long id);
}
