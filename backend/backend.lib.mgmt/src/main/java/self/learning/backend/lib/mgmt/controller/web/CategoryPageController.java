package self.learning.backend.lib.mgmt.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import self.learning.backend.lib.mgmt.dto.request.CategoryCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.CategoryUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.CategoryResponseDto;
import self.learning.backend.lib.mgmt.service.CreateCategoryService;
import self.learning.backend.lib.mgmt.service.DeleteCategoryService;
import self.learning.backend.lib.mgmt.service.SearchCategoryService;
import self.learning.backend.lib.mgmt.service.UpdateCategoryService;
import self.learning.backend.lib.mgmt.service.presenter.CategoryViewService;

@Controller
@RequestMapping("/library/categories")
public class CategoryPageController {

    @Autowired private CategoryViewService viewService;
    @Autowired private CreateCategoryService createService;
    @Autowired private UpdateCategoryService updateService;
    @Autowired private DeleteCategoryService deleteService;
    @Autowired private SearchCategoryService searchService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", viewService.findAll());
        model.addAttribute("searchText", "");
        return "category/list";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam(defaultValue = "") String text,
            Model model) {
        model.addAttribute("searchText", text);
        model.addAttribute(
                "categories",
                text.isBlank()
                        ? viewService.findAll()
                        : searchService.searchCategory(text));
        return "category/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("category", new CategoryCreateRequestDto());
        model.addAttribute("mode", "create");
        return "category/form";
    }

    @PostMapping
    public String create(
            @ModelAttribute("category") CategoryCreateRequestDto request) {
        CategoryResponseDto created = createService.createCategory(request);
        return "redirect:/library/categories/" + created.getCategoryId();
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("category", viewService.findById(id));
        return "category/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        CategoryResponseDto current = viewService.findById(id);
        CategoryUpdateRequestDto form = new CategoryUpdateRequestDto();
        if (current != null) {
            form.setCategoryCode(current.getCategoryCode());
            form.setCategoryName(current.getCategoryName());
        }
        model.addAttribute("category", form);
        model.addAttribute("categoryId", id);
        model.addAttribute("mode", "edit");
        return "category/form";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @ModelAttribute("category") CategoryUpdateRequestDto request) {
        updateService.updateCategory(id, request);
        return "redirect:/library/categories/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deactivate(@PathVariable Long id) {
        deleteService.deleteCategory(id);
        return "redirect:/library/categories";
    }
}
