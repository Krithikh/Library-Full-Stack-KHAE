    package self.learning.backend.lib.mgmt.service.impl;

    import java.util.List;
import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.BookIssueResponseDto;
import self.learning.backend.lib.mgmt.service.SearchBookIssueService;
import java.time.LocalDate;

    @Service
    public class SearchBookIssueServiceImpl implements SearchBookIssueService {
        @Override
    public List<BookIssueResponseDto> searchBookIssue(String text) {
        BookIssueResponseDto response = new BookIssueResponseDto();
        response.setBookIssueId(1L);
        response.setIssueNumber("ISS-0001");
        response.setMembershipId(1L);
        response.setBookCopyId(2L);
        response.setReservationId(null);
        response.setIssueDate(LocalDate.of(2026, 8, 10));
        response.setDueDate(LocalDate.of(2026, 8, 24));
        response.setStatus("ACTIVE");
        return List.of(response);
    }
    }
