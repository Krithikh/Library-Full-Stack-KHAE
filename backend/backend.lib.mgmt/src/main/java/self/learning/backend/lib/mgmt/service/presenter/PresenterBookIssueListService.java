package self.learning.backend.lib.mgmt.service.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import self.learning.backend.lib.mgmt.dao.BookIssueDao;
import self.learning.backend.lib.mgmt.dto.response.BookIssueResponseDto;
import self.learning.backend.lib.mgmt.mapper.BookIssueDtoDoMapper;

@Service
public class PresenterBookIssueListService {

    @Autowired
    private BookIssueDao bookIssueDao;

    @Autowired
    private BookIssueDtoDoMapper mapper;

    public List<BookIssueResponseDto> listBookIssues() {
        return bookIssueDao.findAllCurrent()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
