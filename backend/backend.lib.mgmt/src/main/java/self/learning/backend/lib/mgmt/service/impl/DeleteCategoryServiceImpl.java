    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.CategoryResponseDto;
import self.learning.backend.lib.mgmt.service.DeleteCategoryService;

    @Service
    public class DeleteCategoryServiceImpl implements DeleteCategoryService {
        @Override
    public CategoryResponseDto deleteCategory(Long id) {
        CategoryResponseDto response = new CategoryResponseDto();
        response.setCategoryId(2L);
        response.setCategoryCode("DATABASE");
        response.setCategoryName("Database Systems");
        response.setActive(true);
        response.setCategoryId(id);
        response.setActive(false);
        return response;
    }
    }
