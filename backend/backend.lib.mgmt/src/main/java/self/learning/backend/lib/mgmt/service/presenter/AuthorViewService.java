package self.learning.backend.lib.mgmt.service.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import self.learning.backend.lib.mgmt.dao.AuthorDao;
import self.learning.backend.lib.mgmt.dto.response.AuthorResponseDto;
import self.learning.backend.lib.mgmt.mapper.AuthorDtoDoMapper;

@Service
public class AuthorViewService {

    @Autowired private AuthorDao authorDao;
    @Autowired private AuthorDtoDoMapper mapper;

    public List<AuthorResponseDto> findAll() {
        return authorDao.findAll().stream().map(mapper::toResponse).toList();
    }

    public AuthorResponseDto findById(Long id) {
        return authorDao.findById(id).map(mapper::toResponse).orElse(null);
    }
}
