package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.response.DepartmentResponseDto;

public interface ReadDepartmentService {
    DepartmentResponseDto readDepartment(Long id);
}
