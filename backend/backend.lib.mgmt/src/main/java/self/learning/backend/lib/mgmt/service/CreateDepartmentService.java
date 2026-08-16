package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.DepartmentCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.DepartmentResponseDto;

public interface CreateDepartmentService {
    DepartmentResponseDto createDepartment(DepartmentCreateRequestDto request);
}
