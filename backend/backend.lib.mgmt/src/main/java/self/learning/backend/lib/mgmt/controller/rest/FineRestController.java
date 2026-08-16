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
import self.learning.backend.lib.mgmt.dto.request.FineCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.FineUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.FineResponseDto;
import self.learning.backend.lib.mgmt.service.CreateFineService;
import self.learning.backend.lib.mgmt.service.DeleteFineService;
import self.learning.backend.lib.mgmt.service.ReadFineService;
import self.learning.backend.lib.mgmt.service.SearchFineService;
import self.learning.backend.lib.mgmt.service.UpdateFineService;
import self.learning.backend.lib.mgmt.service.presenter.PresenterFineListService;

@RestController
@RequestMapping("/rest/fines")
public class FineRestController {

    @Autowired private PresenterFineListService presenterListService;
    @Autowired private CreateFineService createService;
    @Autowired private ReadFineService readService;
    @Autowired private UpdateFineService updateService;
    @Autowired private DeleteFineService deleteService;
    @Autowired private SearchFineService searchService;

    @GetMapping
    public ApiResponse<List<FineResponseDto>> list() {
        return ApiResponse.success(
                "P12",
                "Fine List Retrieved Successfully",
                presenterListService.listFines());
    }

    @PostMapping
    public ApiResponse<FineResponseDto> create(
            @RequestBody FineCreateRequestDto request) {
        return ApiResponse.success(
                "56",
                "Fine Created Successfully",
                createService.createFine(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<FineResponseDto> read(@PathVariable Long id) {
        return ApiResponse.success(
                "57",
                "Fine Read Successfully",
                readService.readFine(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<FineResponseDto> update(
            @PathVariable Long id,
            @RequestBody FineUpdateRequestDto request) {
        return ApiResponse.success(
                "58",
                "Fine Updated Successfully",
                updateService.updateFine(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<FineResponseDto> delete(@PathVariable Long id) {
        return ApiResponse.success(
                "59",
                "Fine Voided Successfully",
                deleteService.deleteFine(id));
    }

    @GetMapping("/search")
    public ApiResponse<List<FineResponseDto>> search(
            @RequestParam String text) {
        return ApiResponse.success(
                "60",
                "Fine Search Completed Successfully",
                searchService.searchFine(text));
    }
}
