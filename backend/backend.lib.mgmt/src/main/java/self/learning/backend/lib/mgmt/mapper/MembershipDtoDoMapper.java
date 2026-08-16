package self.learning.backend.lib.mgmt.mapper;

import org.springframework.stereotype.Component;
import self.learning.backend.lib.mgmt.dataobject.MembershipDO;
import self.learning.backend.lib.mgmt.dto.request.MembershipCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.MembershipUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.MembershipResponseDto;

@Component
public class MembershipDtoDoMapper {

    public MembershipDO toDO(MembershipCreateRequestDto request) {
        MembershipDO dataObject = new MembershipDO();
        dataObject.setMembershipNumber(request.getMembershipNumber());
        dataObject.setMemberId(request.getMemberId());
        dataObject.setMembershipType(request.getMembershipType());
        dataObject.setStatus(request.getStatus());
        return dataObject;
    }

    public void applyUpdate(MembershipUpdateRequestDto request, MembershipDO dataObject) {
        dataObject.setMembershipNumber(request.getMembershipNumber());
        dataObject.setMemberId(request.getMemberId());
        dataObject.setMembershipType(request.getMembershipType());
        dataObject.setStatus(request.getStatus());
    }

    public MembershipResponseDto toResponse(MembershipDO dataObject) {
        MembershipResponseDto response = new MembershipResponseDto();
        response.setMembershipId(dataObject.getMembershipId());
        response.setMembershipNumber(dataObject.getMembershipNumber());
        response.setMemberId(dataObject.getMemberId());
        response.setMembershipType(dataObject.getMembershipType());
        response.setStatus(dataObject.getStatus());
        return response;
    }
}
