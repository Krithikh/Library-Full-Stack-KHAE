    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.BookReturnResponseDto;
import self.learning.backend.lib.mgmt.service.UpdateBookReturnService;
import self.learning.backend.lib.mgmt.dto.request.BookReturnUpdateRequestDto;

    @Service
    public class UpdateBookReturnServiceImpl implements UpdateBookReturnService {
        @Override
    public BookReturnResponseDto updateBookReturn(Long id, BookReturnUpdateRequestDto request) {
        BookReturnResponseDto response = new BookReturnResponseDto();
        response.setBookReturnId(id);
        response.setReturnNumber(request.getReturnNumber());
        response.setBookIssueId(request.getBookIssueId());
        response.setReturnDate(request.getReturnDate());
        response.setStatus(request.getStatus());
        return response;
    }
    }
