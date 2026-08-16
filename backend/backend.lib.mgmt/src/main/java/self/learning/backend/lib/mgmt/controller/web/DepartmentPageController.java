package self.learning.backend.lib.mgmt.controller.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import self.learning.backend.lib.mgmt.dto.request.DepartmentCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.DepartmentUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.DepartmentResponseDto;
import self.learning.backend.lib.mgmt.service.CreateDepartmentService;
import self.learning.backend.lib.mgmt.service.DeleteDepartmentService;
import self.learning.backend.lib.mgmt.service.SearchDepartmentService;
import self.learning.backend.lib.mgmt.service.UpdateDepartmentService;
import self.learning.backend.lib.mgmt.service.presenter.DepartmentViewService;

@Controller
@RequestMapping("/library/departments")
public class DepartmentPageController {
    @Autowired private DepartmentViewService viewService;
    @Autowired private CreateDepartmentService createService;
    @Autowired private UpdateDepartmentService updateService;
    @Autowired private DeleteDepartmentService deleteService;
    @Autowired private SearchDepartmentService searchService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("records", viewService.findAll());
        model.addAttribute("searchText", "");
        return "department/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam String text, Model model) {
        List<DepartmentResponseDto> records = searchService.searchDepartment(text);
        model.addAttribute("records", records);
        model.addAttribute("searchText", text);
        return "department/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("form", new DepartmentCreateRequestDto());
        return "department/form-create";
    }

    @PostMapping
    public String create(@ModelAttribute("form") DepartmentCreateRequestDto request) {
        DepartmentResponseDto created = createService.createDepartment(request);
        return "redirect:/library/departments/" + created.getDepartmentId();
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("record", viewService.findById(id));
        model.addAttribute("moduleName", "Department");
        model.addAttribute("listUrl", "/library/departments");
        return "department/detail";
    }

    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable Long id, Model model) {
        DepartmentResponseDto current = viewService.findById(id);
        DepartmentUpdateRequestDto form = new DepartmentUpdateRequestDto();
        form.setDepartmentCode(current.getDepartmentCode());
        form.setDepartmentName(current.getDepartmentName());
        model.addAttribute("departmentId", id);
        model.addAttribute("form", form);
        return "department/form-update";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute("form") DepartmentUpdateRequestDto request) {
        updateService.updateDepartment(id, request);
        return "redirect:/library/departments/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        deleteService.deleteDepartment(id);
        return "redirect:/library/departments";
    }
}
