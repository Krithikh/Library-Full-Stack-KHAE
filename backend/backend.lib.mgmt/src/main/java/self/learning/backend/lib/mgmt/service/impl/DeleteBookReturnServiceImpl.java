    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.BookReturnResponseDto;
import self.learning.backend.lib.mgmt.service.DeleteBookReturnService;
import java.time.LocalDate;

    @Service
    public class DeleteBookReturnServiceImpl implements DeleteBookReturnService {
        @Override
    public BookReturnResponseDto deleteBookReturn(Long id) {
        BookReturnResponseDto response = new BookReturnResponseDto();
        response.setBookReturnId(1L);
        response.setReturnNumber("RET-0001");
        response.setBookIssueId(2L);
        response.setReturnDate(LocalDate.of(2026, 7, 20));
        response.setStatus("COMPLETED");
        response.setBookReturnId(id);
        response.setStatus("VOID");
        return response;
    }
    }
