package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.MembershipUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.MembershipResponseDto;

public interface UpdateMembershipService {
    MembershipResponseDto updateMembership(Long id, MembershipUpdateRequestDto request);
}
