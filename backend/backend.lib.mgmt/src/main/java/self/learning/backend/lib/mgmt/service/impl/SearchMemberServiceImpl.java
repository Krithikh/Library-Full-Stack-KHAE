    package self.learning.backend.lib.mgmt.service.impl;

    import java.util.List;
import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.MemberResponseDto;
import self.learning.backend.lib.mgmt.service.SearchMemberService;

    @Service
    public class SearchMemberServiceImpl implements SearchMemberService {
        @Override
    public List<MemberResponseDto> searchMember(String text) {
        MemberResponseDto response = new MemberResponseDto();
        response.setMemberId(2L);
        response.setRegistrationNumber("REG-ECE-001");
        response.setFullName("Priya Devi");
        response.setEmail("priya@example.edu");
        response.setDepartmentId(2L);
        response.setActive(true);
        return List.of(response);
    }
    }
