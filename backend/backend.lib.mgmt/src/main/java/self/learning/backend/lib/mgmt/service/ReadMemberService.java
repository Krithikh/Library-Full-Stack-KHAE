package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.response.MemberResponseDto;

public interface ReadMemberService {
    MemberResponseDto readMember(Long id);
}
