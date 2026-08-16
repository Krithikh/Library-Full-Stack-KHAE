    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.BookIssueResponseDto;
import self.learning.backend.lib.mgmt.service.UpdateBookIssueService;
import self.learning.backend.lib.mgmt.dto.request.BookIssueUpdateRequestDto;

    @Service
    public class UpdateBookIssueServiceImpl implements UpdateBookIssueService {
        @Override
    public BookIssueResponseDto updateBookIssue(Long id, BookIssueUpdateRequestDto request) {
        BookIssueResponseDto response = new BookIssueResponseDto();
        response.setBookIssueId(id);
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
