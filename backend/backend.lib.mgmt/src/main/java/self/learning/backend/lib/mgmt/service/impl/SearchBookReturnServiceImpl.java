    package self.learning.backend.lib.mgmt.service.impl;

    import java.util.List;
import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.BookReturnResponseDto;
import self.learning.backend.lib.mgmt.service.SearchBookReturnService;
import java.time.LocalDate;

    @Service
    public class SearchBookReturnServiceImpl implements SearchBookReturnService {
        @Override
    public List<BookReturnResponseDto> searchBookReturn(String text) {
        BookReturnResponseDto response = new BookReturnResponseDto();
        response.setBookReturnId(1L);
        response.setReturnNumber("RET-0001");
        response.setBookIssueId(2L);
        response.setReturnDate(LocalDate.of(2026, 7, 20));
        response.setStatus("COMPLETED");
        return List.of(response);
    }
    }
