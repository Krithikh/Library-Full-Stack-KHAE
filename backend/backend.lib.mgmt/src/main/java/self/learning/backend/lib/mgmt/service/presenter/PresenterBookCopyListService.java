package self.learning.backend.lib.mgmt.service.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import self.learning.backend.lib.mgmt.dao.BookCopyDao;
import self.learning.backend.lib.mgmt.dto.response.BookCopyResponseDto;
import self.learning.backend.lib.mgmt.mapper.BookCopyDtoDoMapper;

@Service
public class PresenterBookCopyListService {

    @Autowired
    private BookCopyDao bookCopyDao;

    @Autowired
    private BookCopyDtoDoMapper mapper;

    public List<BookCopyResponseDto> listBookCopies() {
        return bookCopyDao.findAllCurrent()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
