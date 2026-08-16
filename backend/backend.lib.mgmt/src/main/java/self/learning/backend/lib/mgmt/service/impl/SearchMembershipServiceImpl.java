    package self.learning.backend.lib.mgmt.service.impl;

    import java.util.List;
import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.MembershipResponseDto;
import self.learning.backend.lib.mgmt.service.SearchMembershipService;

    @Service
    public class SearchMembershipServiceImpl implements SearchMembershipService {
        @Override
    public List<MembershipResponseDto> searchMembership(String text) {
        MembershipResponseDto response = new MembershipResponseDto();
        response.setMembershipId(2L);
        response.setMembershipNumber("MEM-0002");
        response.setMemberId(2L);
        response.setMembershipType("STUDENT");
        response.setStatus("ACTIVE");
        return List.of(response);
    }
    }
