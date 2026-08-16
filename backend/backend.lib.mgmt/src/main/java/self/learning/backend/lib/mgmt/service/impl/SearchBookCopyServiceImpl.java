    package self.learning.backend.lib.mgmt.service.impl;

    import java.util.List;
import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.BookCopyResponseDto;
import self.learning.backend.lib.mgmt.service.SearchBookCopyService;

    @Service
    public class SearchBookCopyServiceImpl implements SearchBookCopyService {
        @Override
    public List<BookCopyResponseDto> searchBookCopy(String text) {
        BookCopyResponseDto response = new BookCopyResponseDto();
        response.setBookCopyId(1L);
        response.setAccessionNumber("ACC-0001");
        response.setBookId(1L);
        response.setStatus("AVAILABLE");
        return List.of(response);
    }
    }
