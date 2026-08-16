    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.BookCopyResponseDto;
import self.learning.backend.lib.mgmt.service.ReadBookCopyService;

    @Service
    public class ReadBookCopyServiceImpl implements ReadBookCopyService {
        @Override
    public BookCopyResponseDto readBookCopy(Long id) {
        BookCopyResponseDto response = new BookCopyResponseDto();
        response.setBookCopyId(1L);
        response.setAccessionNumber("ACC-0001");
        response.setBookId(1L);
        response.setStatus("AVAILABLE");
        response.setBookCopyId(id);
        return response;
    }
    }
