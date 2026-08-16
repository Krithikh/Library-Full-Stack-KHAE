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

import self.learning.backend.lib.mgmt.contract.ApplicationCodes;
import self.learning.backend.lib.mgmt.dto.ApiResponse;
import self.learning.backend.lib.mgmt.dto.request.DepartmentCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.DepartmentUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.DepartmentResponseDto;
import self.learning.backend.lib.mgmt.service.CreateDepartmentService;
import self.learning.backend.lib.mgmt.service.DeleteDepartmentService;
import self.learning.backend.lib.mgmt.service.ReadDepartmentService;
import self.learning.backend.lib.mgmt.service.SearchDepartmentService;
import self.learning.backend.lib.mgmt.service.UpdateDepartmentService;
import self.learning.backend.lib.mgmt.service.presenter.DepartmentViewService;

@RestController
@RequestMapping("/rest/departments")
public class DepartmentRestController {

    @Autowired private CreateDepartmentService createService;
    @Autowired private ReadDepartmentService readService;
    @Autowired private UpdateDepartmentService updateService;
    @Autowired private DeleteDepartmentService deleteService;
    @Autowired private SearchDepartmentService searchService;
    @Autowired private DepartmentViewService presenterViewService;

    /** Presenter-owned list support used by the student Read/List frontend assignment. */
    @GetMapping
    public ApiResponse<List<DepartmentResponseDto>> list() {
        return ApiResponse.success(ApplicationCodes.P01_LIST_DEPARTMENTS,
                "Department List Read Successfully", presenterViewService.findAll());
    }

    @PostMapping
    public ApiResponse<DepartmentResponseDto> create(@RequestBody DepartmentCreateRequestDto request) {
        return ApiResponse.success(ApplicationCodes.T01_CREATE_DEPARTMENT,
                "Department Created Successfully", createService.createDepartment(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<DepartmentResponseDto> read(@PathVariable Long id) {
        return ApiResponse.success(ApplicationCodes.T02_READ_DEPARTMENT,
                "Department Read Successfully", readService.readDepartment(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<DepartmentResponseDto> update(@PathVariable Long id,
            @RequestBody DepartmentUpdateRequestDto request) {
        return ApiResponse.success(ApplicationCodes.T03_UPDATE_DEPARTMENT,
                "Department Updated Successfully", updateService.updateDepartment(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<DepartmentResponseDto> delete(@PathVariable Long id) {
        return ApiResponse.success(ApplicationCodes.T04_DEACTIVATE_DEPARTMENT,
                "Department Deactivated Successfully", deleteService.deleteDepartment(id));
    }

    @GetMapping("/search")
    public ApiResponse<List<DepartmentResponseDto>> search(@RequestParam String text) {
        return ApiResponse.success(ApplicationCodes.T05_SEARCH_DEPARTMENT,
                "Department Search Completed Successfully", searchService.searchDepartment(text));
    }
}
