package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.response.MembershipResponseDto;

public interface DeleteMembershipService {
    MembershipResponseDto deleteMembership(Long id);
}
