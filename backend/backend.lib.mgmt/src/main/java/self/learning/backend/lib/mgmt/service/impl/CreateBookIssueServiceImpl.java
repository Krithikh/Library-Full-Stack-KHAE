    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.BookIssueResponseDto;
import self.learning.backend.lib.mgmt.service.CreateBookIssueService;
import self.learning.backend.lib.mgmt.dto.request.BookIssueCreateRequestDto;

    @Service
    public class CreateBookIssueServiceImpl implements CreateBookIssueService {
        @Override
    public BookIssueResponseDto createBookIssue(BookIssueCreateRequestDto request) {
        BookIssueResponseDto response = new BookIssueResponseDto();
        response.setBookIssueId(1001L);
        response.setIssueNumber(request.getIssueNumber());
        response.setMembershipId(request.getMembershipId());
        response.setBookCopyId(request.getBookCopyId());
        response.setReservationId(request.getReservationId());
        response.setIssueDate(request.getIssueDate());
        response.setDueDate(request.getDueDate());
        response.setStatus(request.getStatus());
        return response;
    }
    }
