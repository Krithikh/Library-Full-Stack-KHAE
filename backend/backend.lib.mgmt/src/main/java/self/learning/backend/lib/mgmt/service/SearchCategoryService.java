package self.learning.backend.lib.mgmt.service;

import java.util.List;
import self.learning.backend.lib.mgmt.dto.response.CategoryResponseDto;

public interface SearchCategoryService {
    List<CategoryResponseDto> searchCategory(String text);
}
