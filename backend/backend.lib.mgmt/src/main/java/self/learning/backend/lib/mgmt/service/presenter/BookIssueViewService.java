package self.learning.backend.lib.mgmt.service.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import self.learning.backend.lib.mgmt.dao.BookIssueDao;
import self.learning.backend.lib.mgmt.dto.response.BookIssueResponseDto;
import self.learning.backend.lib.mgmt.mapper.BookIssueDtoDoMapper;

@Service
public class BookIssueViewService {

    @Autowired private BookIssueDao bookissueDao;
    @Autowired private BookIssueDtoDoMapper mapper;

    public List<BookIssueResponseDto> findAll() {
        return bookissueDao.findAll().stream().map(mapper::toResponse).toList();
    }

    public BookIssueResponseDto findById(Long id) {
        return bookissueDao.findById(id).map(mapper::toResponse).orElse(null);
    }
}
