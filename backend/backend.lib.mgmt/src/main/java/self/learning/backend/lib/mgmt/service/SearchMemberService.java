package self.learning.backend.lib.mgmt.service;

import java.util.List;
import self.learning.backend.lib.mgmt.dto.response.MemberResponseDto;

public interface SearchMemberService {
    List<MemberResponseDto> searchMember(String text);
}
