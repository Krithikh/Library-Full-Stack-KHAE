package self.learning.backend.lib.mgmt.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import self.learning.backend.lib.mgmt.dto.ApiResponse;
import self.learning.backend.lib.mgmt.dto.request.CategoryCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.CategoryUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.CategoryResponseDto;
import self.learning.backend.lib.mgmt.service.CreateCategoryService;
import self.learning.backend.lib.mgmt.service.DeleteCategoryService;
import self.learning.backend.lib.mgmt.service.ReadCategoryService;
import self.learning.backend.lib.mgmt.service.SearchCategoryService;
import self.learning.backend.lib.mgmt.service.UpdateCategoryService;
import self.learning.backend.lib.mgmt.service.presenter.PresenterCategoryListService;

@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {

    @Autowired private PresenterCategoryListService presenterListService;
    @Autowired private CreateCategoryService createService;
    @Autowired private ReadCategoryService readService;
    @Autowired private UpdateCategoryService updateService;
    @Autowired private DeleteCategoryService deleteService;
    @Autowired private SearchCategoryService searchService;

    @GetMapping
    public ApiResponse<List<CategoryResponseDto>> list() {
        return ApiResponse.success("P04", "Category List Retrieved Successfully",
                presenterListService.listCategories());
    }

    @PostMapping
    public ApiResponse<CategoryResponseDto> create(@RequestBody CategoryCreateRequestDto request) {
        return ApiResponse.success("16", "Category Created Successfully",
                createService.createCategory(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoryResponseDto> read(@PathVariable Long id) {
        return ApiResponse.success("17", "Category Read Successfully",
                readService.readCategory(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<CategoryResponseDto> update(
            @PathVariable Long id,
            @RequestBody CategoryUpdateRequestDto request) {
        return ApiResponse.success("18", "Category Updated Successfully",
                updateService.updateCategory(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<CategoryResponseDto> delete(@PathVariable Long id) {
        return ApiResponse.success("19", "Category Deactivated Successfully",
                deleteService.deleteCategory(id));
    }

    @GetMapping("/search")
    public ApiResponse<List<CategoryResponseDto>> search(@RequestParam String text) {
        return ApiResponse.success("20", "Category Search Completed Successfully",
                searchService.searchCategory(text));
    }
}
