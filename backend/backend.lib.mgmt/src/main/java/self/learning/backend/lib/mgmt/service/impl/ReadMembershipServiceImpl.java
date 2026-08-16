    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.MembershipResponseDto;
import self.learning.backend.lib.mgmt.service.ReadMembershipService;

    @Service
    public class ReadMembershipServiceImpl implements ReadMembershipService {
        @Override
    public MembershipResponseDto readMembership(Long id) {
        MembershipResponseDto response = new MembershipResponseDto();
        response.setMembershipId(2L);
        response.setMembershipNumber("MEM-0002");
        response.setMemberId(2L);
        response.setMembershipType("STUDENT");
        response.setStatus("ACTIVE");
        response.setMembershipId(id);
        return response;
    }
    }
