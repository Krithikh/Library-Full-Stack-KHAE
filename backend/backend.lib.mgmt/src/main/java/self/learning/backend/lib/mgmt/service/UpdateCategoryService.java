package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.CategoryUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.CategoryResponseDto;

public interface UpdateCategoryService {
    CategoryResponseDto updateCategory(Long id, CategoryUpdateRequestDto request);
}
