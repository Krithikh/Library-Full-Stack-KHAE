package self.learning.backend.lib.mgmt.mapper;

import org.springframework.stereotype.Component;
import self.learning.backend.lib.mgmt.dataobject.CategoryDO;
import self.learning.backend.lib.mgmt.dto.request.CategoryCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.CategoryUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.CategoryResponseDto;

@Component
public class CategoryDtoDoMapper {

    public CategoryDO toDO(CategoryCreateRequestDto request) {
        CategoryDO dataObject = new CategoryDO();
        dataObject.setCategoryCode(request.getCategoryCode());
        dataObject.setCategoryName(request.getCategoryName());
        dataObject.setActive(true);
        return dataObject;
    }

    public void applyUpdate(CategoryUpdateRequestDto request, CategoryDO dataObject) {
        dataObject.setCategoryCode(request.getCategoryCode());
        dataObject.setCategoryName(request.getCategoryName());
    }

    public CategoryResponseDto toResponse(CategoryDO dataObject) {
        CategoryResponseDto response = new CategoryResponseDto();
        response.setCategoryId(dataObject.getCategoryId());
        response.setCategoryCode(dataObject.getCategoryCode());
        response.setCategoryName(dataObject.getCategoryName());
        response.setActive(dataObject.getActive());
        return response;
    }
}
