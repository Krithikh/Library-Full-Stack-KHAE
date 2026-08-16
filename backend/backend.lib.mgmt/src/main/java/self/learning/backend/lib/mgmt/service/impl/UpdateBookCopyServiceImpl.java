    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.BookCopyResponseDto;
import self.learning.backend.lib.mgmt.service.UpdateBookCopyService;
import self.learning.backend.lib.mgmt.dto.request.BookCopyUpdateRequestDto;

    @Service
    public class UpdateBookCopyServiceImpl implements UpdateBookCopyService {
        @Override
    public BookCopyResponseDto updateBookCopy(Long id, BookCopyUpdateRequestDto request) {
        BookCopyResponseDto response = new BookCopyResponseDto();
        response.setBookCopyId(id);
        response.setAccessionNumber(request.getAccessionNumber());
        response.setBookId(request.getBookId());
        response.setStatus(request.getStatus());
        return response;
    }
    }
