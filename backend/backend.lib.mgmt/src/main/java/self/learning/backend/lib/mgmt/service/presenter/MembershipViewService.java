package self.learning.backend.lib.mgmt.service.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import self.learning.backend.lib.mgmt.dao.MembershipDao;
import self.learning.backend.lib.mgmt.dto.response.MembershipResponseDto;
import self.learning.backend.lib.mgmt.mapper.MembershipDtoDoMapper;

@Service
public class MembershipViewService {

    @Autowired private MembershipDao membershipDao;
    @Autowired private MembershipDtoDoMapper mapper;

    public List<MembershipResponseDto> findAll() {
        return membershipDao.findAll().stream().map(mapper::toResponse).toList();
    }

    public MembershipResponseDto findById(Long id) {
        return membershipDao.findById(id).map(mapper::toResponse).orElse(null);
    }
}
