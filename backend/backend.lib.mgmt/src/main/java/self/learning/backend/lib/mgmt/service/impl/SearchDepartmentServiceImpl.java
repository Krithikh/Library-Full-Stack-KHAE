    package self.learning.backend.lib.mgmt.service.impl;

    import java.util.List;
import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.DepartmentResponseDto;
import self.learning.backend.lib.mgmt.service.SearchDepartmentService;

    @Service
    public class SearchDepartmentServiceImpl implements SearchDepartmentService {
        @Override
    public List<DepartmentResponseDto> searchDepartment(String text) {
        DepartmentResponseDto response = new DepartmentResponseDto();
        response.setDepartmentId(2L);
        response.setDepartmentCode("ECE");
        response.setDepartmentName("Electronics Engineering");
        response.setActive(true);
        return List.of(response);
    }
    }
