package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.response.MemberResponseDto;

public interface DeleteMemberService {
    MemberResponseDto deleteMember(Long id);
}
