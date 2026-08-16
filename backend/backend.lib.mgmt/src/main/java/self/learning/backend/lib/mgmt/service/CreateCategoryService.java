package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.CategoryCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.CategoryResponseDto;

public interface CreateCategoryService {
    CategoryResponseDto createCategory(CategoryCreateRequestDto request);
}
