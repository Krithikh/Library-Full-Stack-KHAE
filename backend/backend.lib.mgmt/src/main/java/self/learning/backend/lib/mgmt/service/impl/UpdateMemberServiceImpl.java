    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.MemberResponseDto;
import self.learning.backend.lib.mgmt.service.UpdateMemberService;
import self.learning.backend.lib.mgmt.dto.request.MemberUpdateRequestDto;

    @Service
    public class UpdateMemberServiceImpl implements UpdateMemberService {
        @Override
    public MemberResponseDto updateMember(Long id, MemberUpdateRequestDto request) {
        MemberResponseDto response = new MemberResponseDto();
        response.setMemberId(id);
        response.setRegistrationNumber(request.getRegistrationNumber());
        response.setFullName(request.getFullName());
        response.setEmail(request.getEmail());
        response.setDepartmentId(request.getDepartmentId());
        response.setActive(true);
        return response;
    }
    }
