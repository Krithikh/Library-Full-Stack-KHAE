    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.MembershipResponseDto;
import self.learning.backend.lib.mgmt.service.DeleteMembershipService;

    @Service
    public class DeleteMembershipServiceImpl implements DeleteMembershipService {
        @Override
    public MembershipResponseDto deleteMembership(Long id) {
        MembershipResponseDto response = new MembershipResponseDto();
        response.setMembershipId(2L);
        response.setMembershipNumber("MEM-0002");
        response.setMemberId(2L);
        response.setMembershipType("STUDENT");
        response.setStatus("ACTIVE");
        response.setMembershipId(id);
        response.setStatus("INACTIVE");
        return response;
    }
    }
