    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.DepartmentResponseDto;
import self.learning.backend.lib.mgmt.service.CreateDepartmentService;
import self.learning.backend.lib.mgmt.dto.request.DepartmentCreateRequestDto;

    @Service
    public class CreateDepartmentServiceImpl implements CreateDepartmentService {
        @Override
    public DepartmentResponseDto createDepartment(DepartmentCreateRequestDto request) {
        DepartmentResponseDto response = new DepartmentResponseDto();
        response.setDepartmentId(1001L);
        response.setDepartmentCode(request.getDepartmentCode());
        response.setDepartmentName(request.getDepartmentName());
        response.setActive(true);
        return response;
    }
    }
