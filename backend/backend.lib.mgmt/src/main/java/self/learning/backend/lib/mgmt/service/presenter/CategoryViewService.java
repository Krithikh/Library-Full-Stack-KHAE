package self.learning.backend.lib.mgmt.service.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import self.learning.backend.lib.mgmt.dao.CategoryDao;
import self.learning.backend.lib.mgmt.dto.response.CategoryResponseDto;
import self.learning.backend.lib.mgmt.mapper.CategoryDtoDoMapper;

@Service
public class CategoryViewService {

    @Autowired private CategoryDao categoryDao;
    @Autowired private CategoryDtoDoMapper mapper;

    public List<CategoryResponseDto> findAll() {
        return categoryDao.findAll().stream().map(mapper::toResponse).toList();
    }

    public CategoryResponseDto findById(Long id) {
        return categoryDao.findById(id).map(mapper::toResponse).orElse(null);
    }
}
