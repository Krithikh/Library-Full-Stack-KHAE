    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.BookResponseDto;
import self.learning.backend.lib.mgmt.service.UpdateBookService;
import self.learning.backend.lib.mgmt.dto.request.BookUpdateRequestDto;

    @Service
    public class UpdateBookServiceImpl implements UpdateBookService {
        @Override
    public BookResponseDto updateBook(Long id, BookUpdateRequestDto request) {
        BookResponseDto response = new BookResponseDto();
        response.setBookId(id);
        response.setIsbn(request.getIsbn());
        response.setTitle(request.getTitle());
        response.setAuthorId(request.getAuthorId());
        response.setCategoryId(request.getCategoryId());
        response.setPublisherId(request.getPublisherId());
        response.setActive(true);
        return response;
    }
    }
