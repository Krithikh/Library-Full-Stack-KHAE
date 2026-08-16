    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.CategoryResponseDto;
import self.learning.backend.lib.mgmt.service.UpdateCategoryService;
import self.learning.backend.lib.mgmt.dto.request.CategoryUpdateRequestDto;

    @Service
    public class UpdateCategoryServiceImpl implements UpdateCategoryService {
        @Override
    public CategoryResponseDto updateCategory(Long id, CategoryUpdateRequestDto request) {
        CategoryResponseDto response = new CategoryResponseDto();
        response.setCategoryId(id);
        response.setCategoryCode(request.getCategoryCode());
        response.setCategoryName(request.getCategoryName());
        response.setActive(true);
        return response;
    }
    }
