package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.response.MembershipResponseDto;

public interface ReadMembershipService {
    MembershipResponseDto readMembership(Long id);
}
