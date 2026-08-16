    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.BookResponseDto;
import self.learning.backend.lib.mgmt.service.ReadBookService;

    @Service
    public class ReadBookServiceImpl implements ReadBookService {
        @Override
    public BookResponseDto readBook(Long id) {
        BookResponseDto response = new BookResponseDto();
        response.setBookId(2L);
        response.setIsbn("9780134685991");
        response.setTitle("Effective Java");
        response.setAuthorId(2L);
        response.setCategoryId(1L);
        response.setPublisherId(2L);
        response.setActive(true);
        response.setBookId(id);
        return response;
    }
    }
