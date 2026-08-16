    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.MembershipResponseDto;
import self.learning.backend.lib.mgmt.service.UpdateMembershipService;
import self.learning.backend.lib.mgmt.dto.request.MembershipUpdateRequestDto;

    @Service
    public class UpdateMembershipServiceImpl implements UpdateMembershipService {
        @Override
    public MembershipResponseDto updateMembership(Long id, MembershipUpdateRequestDto request) {
        MembershipResponseDto response = new MembershipResponseDto();
        response.setMembershipId(id);
        response.setMembershipNumber(request.getMembershipNumber());
        response.setMemberId(request.getMemberId());
        response.setMembershipType(request.getMembershipType());
        response.setStatus(request.getStatus());
        return response;
    }
    }
