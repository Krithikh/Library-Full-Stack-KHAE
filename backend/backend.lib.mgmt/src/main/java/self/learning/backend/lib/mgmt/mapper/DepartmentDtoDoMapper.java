package self.learning.backend.lib.mgmt.mapper;

import org.springframework.stereotype.Component;
import self.learning.backend.lib.mgmt.dataobject.DepartmentDO;
import self.learning.backend.lib.mgmt.dto.request.DepartmentCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.DepartmentUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.DepartmentResponseDto;

@Component
public class DepartmentDtoDoMapper {

    public DepartmentDO toDO(DepartmentCreateRequestDto request) {
        DepartmentDO dataObject = new DepartmentDO();
        dataObject.setDepartmentCode(request.getDepartmentCode());
        dataObject.setDepartmentName(request.getDepartmentName());
        dataObject.setActive(true);
        return dataObject;
    }

    public void applyUpdate(DepartmentUpdateRequestDto request, DepartmentDO dataObject) {
        dataObject.setDepartmentCode(request.getDepartmentCode());
        dataObject.setDepartmentName(request.getDepartmentName());
    }

    public DepartmentResponseDto toResponse(DepartmentDO dataObject) {
        DepartmentResponseDto response = new DepartmentResponseDto();
        response.setDepartmentId(dataObject.getDepartmentId());
        response.setDepartmentCode(dataObject.getDepartmentCode());
        response.setDepartmentName(dataObject.getDepartmentName());
        response.setActive(dataObject.getActive());
        return response;
    }
}
