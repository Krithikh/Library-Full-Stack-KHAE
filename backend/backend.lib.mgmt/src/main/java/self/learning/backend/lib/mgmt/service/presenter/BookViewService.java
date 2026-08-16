package self.learning.backend.lib.mgmt.service.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import self.learning.backend.lib.mgmt.dao.BookDao;
import self.learning.backend.lib.mgmt.dto.response.BookResponseDto;
import self.learning.backend.lib.mgmt.mapper.BookDtoDoMapper;

@Service
public class BookViewService {

    @Autowired private BookDao bookDao;
    @Autowired private BookDtoDoMapper mapper;

    public List<BookResponseDto> findAll() {
        return bookDao.findAll().stream().map(mapper::toResponse).toList();
    }

    public BookResponseDto findById(Long id) {
        return bookDao.findById(id).map(mapper::toResponse).orElse(null);
    }
}
