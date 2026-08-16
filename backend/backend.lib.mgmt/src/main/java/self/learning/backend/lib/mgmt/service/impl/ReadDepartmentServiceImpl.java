    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.DepartmentResponseDto;
import self.learning.backend.lib.mgmt.service.ReadDepartmentService;

    @Service
    public class ReadDepartmentServiceImpl implements ReadDepartmentService {
        @Override
    public DepartmentResponseDto readDepartment(Long id) {
        DepartmentResponseDto response = new DepartmentResponseDto();
        response.setDepartmentId(2L);
        response.setDepartmentCode("ECE");
        response.setDepartmentName("Electronics Engineering");
        response.setActive(true);
        response.setDepartmentId(id);
        return response;
    }
    }
