package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.MembershipCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.MembershipResponseDto;

public interface CreateMembershipService {
    MembershipResponseDto createMembership(MembershipCreateRequestDto request);
}
