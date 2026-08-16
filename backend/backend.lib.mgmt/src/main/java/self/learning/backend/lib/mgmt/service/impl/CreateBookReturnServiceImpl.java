    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.BookReturnResponseDto;
import self.learning.backend.lib.mgmt.service.CreateBookReturnService;
import self.learning.backend.lib.mgmt.dto.request.BookReturnCreateRequestDto;

    @Service
    public class CreateBookReturnServiceImpl implements CreateBookReturnService {
        @Override
    public BookReturnResponseDto createBookReturn(BookReturnCreateRequestDto request) {
        BookReturnResponseDto response = new BookReturnResponseDto();
        response.setBookReturnId(1001L);
        response.setReturnNumber(request.getReturnNumber());
        response.setBookIssueId(request.getBookIssueId());
        response.setReturnDate(request.getReturnDate());
        response.setStatus(request.getStatus());
        return response;
    }
    }
