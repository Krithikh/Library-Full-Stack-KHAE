package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.response.CategoryResponseDto;

public interface DeleteCategoryService {
    CategoryResponseDto deleteCategory(Long id);
}
