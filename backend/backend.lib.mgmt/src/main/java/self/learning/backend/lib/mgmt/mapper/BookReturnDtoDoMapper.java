package self.learning.backend.lib.mgmt.mapper;

import org.springframework.stereotype.Component;
import self.learning.backend.lib.mgmt.dataobject.BookReturnDO;
import self.learning.backend.lib.mgmt.dto.request.BookReturnCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.BookReturnUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.BookReturnResponseDto;

@Component
public class BookReturnDtoDoMapper {

    public BookReturnDO toDO(BookReturnCreateRequestDto request) {
        BookReturnDO dataObject = new BookReturnDO();
        dataObject.setReturnNumber(request.getReturnNumber());
        dataObject.setBookIssueId(request.getBookIssueId());
        dataObject.setReturnDate(request.getReturnDate());
        dataObject.setStatus(request.getStatus());
        return dataObject;
    }

    public void applyUpdate(BookReturnUpdateRequestDto request, BookReturnDO dataObject) {
        dataObject.setReturnNumber(request.getReturnNumber());
        dataObject.setBookIssueId(request.getBookIssueId());
        dataObject.setReturnDate(request.getReturnDate());
        dataObject.setStatus(request.getStatus());
    }

    public BookReturnResponseDto toResponse(BookReturnDO dataObject) {
        BookReturnResponseDto response = new BookReturnResponseDto();
        response.setBookReturnId(dataObject.getBookReturnId());
        response.setReturnNumber(dataObject.getReturnNumber());
        response.setBookIssueId(dataObject.getBookIssueId());
        response.setReturnDate(dataObject.getReturnDate());
        response.setStatus(dataObject.getStatus());
        return response;
    }
}
