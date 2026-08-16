package self.learning.backend.lib.mgmt.mapper;

import org.springframework.stereotype.Component;
import self.learning.backend.lib.mgmt.dataobject.BookIssueDO;
import self.learning.backend.lib.mgmt.dto.request.BookIssueCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.BookIssueUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.BookIssueResponseDto;

@Component
public class BookIssueDtoDoMapper {

    public BookIssueDO toDO(BookIssueCreateRequestDto request) {
        BookIssueDO dataObject = new BookIssueDO();
        dataObject.setIssueNumber(request.getIssueNumber());
        dataObject.setMembershipId(request.getMembershipId());
        dataObject.setBookCopyId(request.getBookCopyId());
        dataObject.setReservationId(request.getReservationId());
        dataObject.setIssueDate(request.getIssueDate());
        dataObject.setDueDate(request.getDueDate());
        dataObject.setStatus(request.getStatus());
        return dataObject;
    }

    public void applyUpdate(BookIssueUpdateRequestDto request, BookIssueDO dataObject) {
        dataObject.setIssueNumber(request.getIssueNumber());
        dataObject.setMembershipId(request.getMembershipId());
        dataObject.setBookCopyId(request.getBookCopyId());
        dataObject.setReservationId(request.getReservationId());
        dataObject.setIssueDate(request.getIssueDate());
        dataObject.setDueDate(request.getDueDate());
        dataObject.setStatus(request.getStatus());
    }

    public BookIssueResponseDto toResponse(BookIssueDO dataObject) {
        BookIssueResponseDto response = new BookIssueResponseDto();
        response.setBookIssueId(dataObject.getBookIssueId());
        response.setIssueNumber(dataObject.getIssueNumber());
        response.setMembershipId(dataObject.getMembershipId());
        response.setBookCopyId(dataObject.getBookCopyId());
        response.setReservationId(dataObject.getReservationId());
        response.setIssueDate(dataObject.getIssueDate());
        response.setDueDate(dataObject.getDueDate());
        response.setStatus(dataObject.getStatus());
        return response;
    }
}
