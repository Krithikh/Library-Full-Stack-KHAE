package self.learning.backend.lib.mgmt.mapper;

import org.springframework.stereotype.Component;
import self.learning.backend.lib.mgmt.dataobject.BookDO;
import self.learning.backend.lib.mgmt.dto.request.BookCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.BookUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.BookResponseDto;

@Component
public class BookDtoDoMapper {

    public BookDO toDO(BookCreateRequestDto request) {
        BookDO dataObject = new BookDO();
        dataObject.setIsbn(request.getIsbn());
        dataObject.setTitle(request.getTitle());
        dataObject.setAuthorId(request.getAuthorId());
        dataObject.setCategoryId(request.getCategoryId());
        dataObject.setPublisherId(request.getPublisherId());
        dataObject.setActive(true);
        return dataObject;
    }

    public void applyUpdate(BookUpdateRequestDto request, BookDO dataObject) {
        dataObject.setIsbn(request.getIsbn());
        dataObject.setTitle(request.getTitle());
        dataObject.setAuthorId(request.getAuthorId());
        dataObject.setCategoryId(request.getCategoryId());
        dataObject.setPublisherId(request.getPublisherId());
    }

    public BookResponseDto toResponse(BookDO dataObject) {
        BookResponseDto response = new BookResponseDto();
        response.setBookId(dataObject.getBookId());
        response.setIsbn(dataObject.getIsbn());
        response.setTitle(dataObject.getTitle());
        response.setAuthorId(dataObject.getAuthorId());
        response.setCategoryId(dataObject.getCategoryId());
        response.setPublisherId(dataObject.getPublisherId());
        response.setActive(dataObject.getActive());
        return response;
    }
}
