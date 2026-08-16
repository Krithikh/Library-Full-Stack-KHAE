package self.learning.backend.lib.mgmt.service.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import self.learning.backend.lib.mgmt.dao.BookReturnDao;
import self.learning.backend.lib.mgmt.dto.response.BookReturnResponseDto;
import self.learning.backend.lib.mgmt.mapper.BookReturnDtoDoMapper;

@Service
public class PresenterBookReturnListService {

    @Autowired
    private BookReturnDao bookReturnDao;

    @Autowired
    private BookReturnDtoDoMapper mapper;

    public List<BookReturnResponseDto> listBookReturns() {
        return bookReturnDao.findAllCurrent()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
