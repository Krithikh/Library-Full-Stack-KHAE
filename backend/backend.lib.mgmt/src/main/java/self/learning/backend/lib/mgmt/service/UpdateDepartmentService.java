package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.DepartmentUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.DepartmentResponseDto;

public interface UpdateDepartmentService {
    DepartmentResponseDto updateDepartment(Long id, DepartmentUpdateRequestDto request);
}
