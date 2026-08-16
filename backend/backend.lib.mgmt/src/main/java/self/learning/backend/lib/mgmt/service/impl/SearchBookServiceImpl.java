    package self.learning.backend.lib.mgmt.service.impl;

    import java.util.List;
import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.BookResponseDto;
import self.learning.backend.lib.mgmt.service.SearchBookService;

    @Service
    public class SearchBookServiceImpl implements SearchBookService {
        @Override
    public List<BookResponseDto> searchBook(String text) {
        BookResponseDto response = new BookResponseDto();
        response.setBookId(2L);
        response.setIsbn("9780134685991");
        response.setTitle("Effective Java");
        response.setAuthorId(2L);
        response.setCategoryId(1L);
        response.setPublisherId(2L);
        response.setActive(true);
        return List.of(response);
    }
    }
