package self.learning.backend.lib.mgmt.service.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import self.learning.backend.lib.mgmt.dao.BookCopyDao;
import self.learning.backend.lib.mgmt.dto.response.BookCopyResponseDto;
import self.learning.backend.lib.mgmt.mapper.BookCopyDtoDoMapper;

@Service
public class BookCopyViewService {

    @Autowired private BookCopyDao bookcopyDao;
    @Autowired private BookCopyDtoDoMapper mapper;

    public List<BookCopyResponseDto> findAll() {
        return bookcopyDao.findAll().stream().map(mapper::toResponse).toList();
    }

    public BookCopyResponseDto findById(Long id) {
        return bookcopyDao.findById(id).map(mapper::toResponse).orElse(null);
    }
}
