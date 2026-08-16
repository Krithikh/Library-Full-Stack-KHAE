    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.CategoryResponseDto;
import self.learning.backend.lib.mgmt.service.CreateCategoryService;
import self.learning.backend.lib.mgmt.dto.request.CategoryCreateRequestDto;

    @Service
    public class CreateCategoryServiceImpl implements CreateCategoryService {
        @Override
    public CategoryResponseDto createCategory(CategoryCreateRequestDto request) {
        CategoryResponseDto response = new CategoryResponseDto();
        response.setCategoryId(1001L);
        response.setCategoryCode(request.getCategoryCode());
        response.setCategoryName(request.getCategoryName());
        response.setActive(true);
        return response;
    }
    }
