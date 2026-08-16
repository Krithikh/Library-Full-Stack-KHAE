package self.learning.backend.lib.mgmt.service.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import self.learning.backend.lib.mgmt.dao.AuthorDao;
import self.learning.backend.lib.mgmt.dto.response.AuthorResponseDto;
import self.learning.backend.lib.mgmt.mapper.AuthorDtoDoMapper;

@Service
public class PresenterAuthorListService {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private AuthorDtoDoMapper mapper;

    public List<AuthorResponseDto> listAuthors() {
        return authorDao.findAllCurrent()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
