package self.learning.backend.lib.mgmt.service;

import java.util.List;
import self.learning.backend.lib.mgmt.dto.response.DepartmentResponseDto;

public interface SearchDepartmentService {
    List<DepartmentResponseDto> searchDepartment(String text);
}
