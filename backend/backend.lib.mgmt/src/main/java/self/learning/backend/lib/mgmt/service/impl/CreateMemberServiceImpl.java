    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.MemberResponseDto;
import self.learning.backend.lib.mgmt.service.CreateMemberService;
import self.learning.backend.lib.mgmt.dto.request.MemberCreateRequestDto;

    @Service
    public class CreateMemberServiceImpl implements CreateMemberService {
        @Override
    public MemberResponseDto createMember(MemberCreateRequestDto request) {
        MemberResponseDto response = new MemberResponseDto();
        response.setMemberId(1001L);
        response.setRegistrationNumber(request.getRegistrationNumber());
        response.setFullName(request.getFullName());
        response.setEmail(request.getEmail());
        response.setDepartmentId(request.getDepartmentId());
        response.setActive(true);
        return response;
    }
    }
