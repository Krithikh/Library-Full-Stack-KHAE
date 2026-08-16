package self.learning.backend.lib.mgmt.mapper;

import org.springframework.stereotype.Component;
import self.learning.backend.lib.mgmt.dataobject.BookCopyDO;
import self.learning.backend.lib.mgmt.dto.request.BookCopyCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.BookCopyUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.BookCopyResponseDto;

@Component
public class BookCopyDtoDoMapper {

    public BookCopyDO toDO(BookCopyCreateRequestDto request) {
        BookCopyDO dataObject = new BookCopyDO();
        dataObject.setAccessionNumber(request.getAccessionNumber());
        dataObject.setBookId(request.getBookId());
        dataObject.setStatus(request.getStatus());
        return dataObject;
    }

    public void applyUpdate(BookCopyUpdateRequestDto request, BookCopyDO dataObject) {
        dataObject.setAccessionNumber(request.getAccessionNumber());
        dataObject.setBookId(request.getBookId());
        dataObject.setStatus(request.getStatus());
    }

    public BookCopyResponseDto toResponse(BookCopyDO dataObject) {
        BookCopyResponseDto response = new BookCopyResponseDto();
        response.setBookCopyId(dataObject.getBookCopyId());
        response.setAccessionNumber(dataObject.getAccessionNumber());
        response.setBookId(dataObject.getBookId());
        response.setStatus(dataObject.getStatus());
        return response;
    }
}
