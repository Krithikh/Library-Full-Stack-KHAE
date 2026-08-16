    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.MemberResponseDto;
import self.learning.backend.lib.mgmt.service.ReadMemberService;

    @Service
    public class ReadMemberServiceImpl implements ReadMemberService {
        @Override
    public MemberResponseDto readMember(Long id) {
        MemberResponseDto response = new MemberResponseDto();
        response.setMemberId(2L);
        response.setRegistrationNumber("REG-ECE-001");
        response.setFullName("Priya Devi");
        response.setEmail("priya@example.edu");
        response.setDepartmentId(2L);
        response.setActive(true);
        response.setMemberId(id);
        return response;
    }
    }
