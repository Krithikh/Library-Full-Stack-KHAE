    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.MembershipResponseDto;
import self.learning.backend.lib.mgmt.service.CreateMembershipService;
import self.learning.backend.lib.mgmt.dto.request.MembershipCreateRequestDto;

    @Service
    public class CreateMembershipServiceImpl implements CreateMembershipService {
        @Override
    public MembershipResponseDto createMembership(MembershipCreateRequestDto request) {
        MembershipResponseDto response = new MembershipResponseDto();
        response.setMembershipId(1001L);
        response.setMembershipNumber(request.getMembershipNumber());
        response.setMemberId(request.getMemberId());
        response.setMembershipType(request.getMembershipType());
        response.setStatus(request.getStatus());
        return response;
    }
    }
