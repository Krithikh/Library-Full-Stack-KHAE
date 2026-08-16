    package self.learning.backend.lib.mgmt.service.impl;

    import java.util.List;
import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.CategoryResponseDto;
import self.learning.backend.lib.mgmt.service.SearchCategoryService;

    @Service
    public class SearchCategoryServiceImpl implements SearchCategoryService {
        @Override
    public List<CategoryResponseDto> searchCategory(String text) {
        CategoryResponseDto response = new CategoryResponseDto();
        response.setCategoryId(2L);
        response.setCategoryCode("DATABASE");
        response.setCategoryName("Database Systems");
        response.setActive(true);
        return List.of(response);
    }
    }
