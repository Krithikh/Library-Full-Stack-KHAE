package self.learning.backend.lib.mgmt.service.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import self.learning.backend.lib.mgmt.dao.BookReturnDao;
import self.learning.backend.lib.mgmt.dto.response.BookReturnResponseDto;
import self.learning.backend.lib.mgmt.mapper.BookReturnDtoDoMapper;

@Service
public class BookReturnViewService {

    @Autowired private BookReturnDao bookreturnDao;
    @Autowired private BookReturnDtoDoMapper mapper;

    public List<BookReturnResponseDto> findAll() {
        return bookreturnDao.findAll().stream().map(mapper::toResponse).toList();
    }

    public BookReturnResponseDto findById(Long id) {
        return bookreturnDao.findById(id).map(mapper::toResponse).orElse(null);
    }
}
