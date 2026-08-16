package self.learning.backend.lib.mgmt.mapper;

import org.springframework.stereotype.Component;
import self.learning.backend.lib.mgmt.dataobject.MemberDO;
import self.learning.backend.lib.mgmt.dto.request.MemberCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.MemberUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.MemberResponseDto;

@Component
public class MemberDtoDoMapper {

    public MemberDO toDO(MemberCreateRequestDto request) {
        MemberDO dataObject = new MemberDO();
        dataObject.setRegistrationNumber(request.getRegistrationNumber());
        dataObject.setFullName(request.getFullName());
        dataObject.setEmail(request.getEmail());
        dataObject.setDepartmentId(request.getDepartmentId());
        dataObject.setActive(true);
        return dataObject;
    }

    public void applyUpdate(MemberUpdateRequestDto request, MemberDO dataObject) {
        dataObject.setRegistrationNumber(request.getRegistrationNumber());
        dataObject.setFullName(request.getFullName());
        dataObject.setEmail(request.getEmail());
        dataObject.setDepartmentId(request.getDepartmentId());
    }

    public MemberResponseDto toResponse(MemberDO dataObject) {
        MemberResponseDto response = new MemberResponseDto();
        response.setMemberId(dataObject.getMemberId());
        response.setRegistrationNumber(dataObject.getRegistrationNumber());
        response.setFullName(dataObject.getFullName());
        response.setEmail(dataObject.getEmail());
        response.setDepartmentId(dataObject.getDepartmentId());
        response.setActive(dataObject.getActive());
        return response;
    }
}
