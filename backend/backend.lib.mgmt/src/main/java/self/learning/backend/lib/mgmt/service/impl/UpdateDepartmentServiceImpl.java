    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.DepartmentResponseDto;
import self.learning.backend.lib.mgmt.service.UpdateDepartmentService;
import self.learning.backend.lib.mgmt.dto.request.DepartmentUpdateRequestDto;

    @Service
    public class UpdateDepartmentServiceImpl implements UpdateDepartmentService {
        @Override
    public DepartmentResponseDto updateDepartment(Long id, DepartmentUpdateRequestDto request) {
        DepartmentResponseDto response = new DepartmentResponseDto();
        response.setDepartmentId(id);
        response.setDepartmentCode(request.getDepartmentCode());
        response.setDepartmentName(request.getDepartmentName());
        response.setActive(true);
        return response;
    }
    }
