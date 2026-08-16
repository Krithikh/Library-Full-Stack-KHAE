package self.learning.backend.lib.mgmt.service.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import self.learning.backend.lib.mgmt.dao.MembershipDao;
import self.learning.backend.lib.mgmt.dto.response.MembershipResponseDto;
import self.learning.backend.lib.mgmt.mapper.MembershipDtoDoMapper;

@Service
public class PresenterMembershipListService {

    @Autowired
    private MembershipDao membershipDao;

    @Autowired
    private MembershipDtoDoMapper mapper;

    public List<MembershipResponseDto> listMemberships() {
        return membershipDao.findAllCurrent()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
