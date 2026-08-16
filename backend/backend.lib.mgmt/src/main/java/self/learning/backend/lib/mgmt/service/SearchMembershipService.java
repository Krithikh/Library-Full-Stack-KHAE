package self.learning.backend.lib.mgmt.service;

import java.util.List;
import self.learning.backend.lib.mgmt.dto.response.MembershipResponseDto;

public interface SearchMembershipService {
    List<MembershipResponseDto> searchMembership(String text);
}
