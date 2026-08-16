    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.CategoryResponseDto;
import self.learning.backend.lib.mgmt.service.ReadCategoryService;

    @Service
    public class ReadCategoryServiceImpl implements ReadCategoryService {
        @Override
    public CategoryResponseDto readCategory(Long id) {
        CategoryResponseDto response = new CategoryResponseDto();
        response.setCategoryId(2L);
        response.setCategoryCode("DATABASE");
        response.setCategoryName("Database Systems");
        response.setActive(true);
        response.setCategoryId(id);
        return response;
    }
    }
