package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.MemberCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.MemberResponseDto;

public interface CreateMemberService {
    MemberResponseDto createMember(MemberCreateRequestDto request);
}
