    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.BookResponseDto;
import self.learning.backend.lib.mgmt.service.CreateBookService;
import self.learning.backend.lib.mgmt.dto.request.BookCreateRequestDto;

    @Service
    public class CreateBookServiceImpl implements CreateBookService {
        @Override
    public BookResponseDto createBook(BookCreateRequestDto request) {
        BookResponseDto response = new BookResponseDto();
        response.setBookId(1001L);
        response.setIsbn(request.getIsbn());
        response.setTitle(request.getTitle());
        response.setAuthorId(request.getAuthorId());
        response.setCategoryId(request.getCategoryId());
        response.setPublisherId(request.getPublisherId());
        response.setActive(true);
        return response;
    }
    }
