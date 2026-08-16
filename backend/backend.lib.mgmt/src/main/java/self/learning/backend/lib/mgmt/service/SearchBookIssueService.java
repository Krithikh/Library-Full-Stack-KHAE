package self.learning.backend.lib.mgmt.service;

import java.util.List;
import self.learning.backend.lib.mgmt.dto.response.BookIssueResponseDto;

public interface SearchBookIssueService {
    List<BookIssueResponseDto> searchBookIssue(String text);
}
