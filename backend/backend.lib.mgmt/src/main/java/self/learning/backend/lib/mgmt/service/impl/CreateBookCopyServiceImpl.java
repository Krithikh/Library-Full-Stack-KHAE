    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.BookCopyResponseDto;
import self.learning.backend.lib.mgmt.service.CreateBookCopyService;
import self.learning.backend.lib.mgmt.dto.request.BookCopyCreateRequestDto;

    @Service
    public class CreateBookCopyServiceImpl implements CreateBookCopyService {
        @Override
    public BookCopyResponseDto createBookCopy(BookCopyCreateRequestDto request) {
        BookCopyResponseDto response = new BookCopyResponseDto();
        response.setBookCopyId(1001L);
        response.setAccessionNumber(request.getAccessionNumber());
        response.setBookId(request.getBookId());
        response.setStatus(request.getStatus());
        return response;
    }
    }
