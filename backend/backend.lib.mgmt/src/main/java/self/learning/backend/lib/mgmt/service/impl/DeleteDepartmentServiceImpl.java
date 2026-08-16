    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.DepartmentResponseDto;
import self.learning.backend.lib.mgmt.service.DeleteDepartmentService;

    @Service
    public class DeleteDepartmentServiceImpl implements DeleteDepartmentService {
        @Override
    public DepartmentResponseDto deleteDepartment(Long id) {
        DepartmentResponseDto response = new DepartmentResponseDto();
        response.setDepartmentId(2L);
        response.setDepartmentCode("ECE");
        response.setDepartmentName("Electronics Engineering");
        response.setActive(true);
        response.setDepartmentId(id);
        response.setActive(false);
        return response;
    }
    }
