package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.MemberUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.MemberResponseDto;

public interface UpdateMemberService {
    MemberResponseDto updateMember(Long id, MemberUpdateRequestDto request);
}
