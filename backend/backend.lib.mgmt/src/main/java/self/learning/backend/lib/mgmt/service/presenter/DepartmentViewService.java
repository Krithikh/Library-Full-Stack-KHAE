package self.learning.backend.lib.mgmt.service.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import self.learning.backend.lib.mgmt.dao.DepartmentDao;
import self.learning.backend.lib.mgmt.dto.response.DepartmentResponseDto;
import self.learning.backend.lib.mgmt.mapper.DepartmentDtoDoMapper;

@Service
public class DepartmentViewService {

    @Autowired private DepartmentDao departmentDao;
    @Autowired private DepartmentDtoDoMapper mapper;

    public List<DepartmentResponseDto> findAll() {
        return departmentDao.findAll().stream().map(mapper::toResponse).toList();
    }

    public DepartmentResponseDto findById(Long id) {
        return departmentDao.findById(id).map(mapper::toResponse).orElse(null);
    }
}
