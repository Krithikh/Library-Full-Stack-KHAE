package self.learning.backend.lib.mgmt.service.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import self.learning.backend.lib.mgmt.dao.MemberDao;
import self.learning.backend.lib.mgmt.dto.response.MemberResponseDto;
import self.learning.backend.lib.mgmt.mapper.MemberDtoDoMapper;

@Service
public class MemberViewService {

    @Autowired private MemberDao memberDao;
    @Autowired private MemberDtoDoMapper mapper;

    public List<MemberResponseDto> findAll() {
        return memberDao.findAll().stream().map(mapper::toResponse).toList();
    }

    public MemberResponseDto findById(Long id) {
        return memberDao.findById(id).map(mapper::toResponse).orElse(null);
    }
}
