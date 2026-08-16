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
import self.learning.backend.lib.mgmt.dto.request.PublisherCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.PublisherUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.PublisherResponseDto;
import self.learning.backend.lib.mgmt.service.CreatePublisherService;
import self.learning.backend.lib.mgmt.service.DeletePublisherService;
import self.learning.backend.lib.mgmt.service.ReadPublisherService;
import self.learning.backend.lib.mgmt.service.SearchPublisherService;
import self.learning.backend.lib.mgmt.service.UpdatePublisherService;
import self.learning.backend.lib.mgmt.service.presenter.PresenterPublisherListService;

@RestController
@RequestMapping("/rest/publishers")
public class PublisherRestController {

    @Autowired private PresenterPublisherListService presenterListService;
    @Autowired private CreatePublisherService createService;
    @Autowired private ReadPublisherService readService;
    @Autowired private UpdatePublisherService updateService;
    @Autowired private DeletePublisherService deleteService;
    @Autowired private SearchPublisherService searchService;

    @GetMapping
    public ApiResponse<List<PublisherResponseDto>> list() {
        return ApiResponse.success("P05", "Publisher List Retrieved Successfully",
                presenterListService.listPublishers());
    }

    @PostMapping
    public ApiResponse<PublisherResponseDto> create(@RequestBody PublisherCreateRequestDto request) {
        return ApiResponse.success("21", "Publisher Created Successfully",
                createService.createPublisher(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<PublisherResponseDto> read(@PathVariable Long id) {
        return ApiResponse.success("22", "Publisher Read Successfully",
                readService.readPublisher(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<PublisherResponseDto> update(
            @PathVariable Long id,
            @RequestBody PublisherUpdateRequestDto request) {
        return ApiResponse.success("23", "Publisher Updated Successfully",
                updateService.updatePublisher(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<PublisherResponseDto> delete(@PathVariable Long id) {
        return ApiResponse.success("24", "Publisher Deactivated Successfully",
                deleteService.deletePublisher(id));
    }

    @GetMapping("/search")
    public ApiResponse<List<PublisherResponseDto>> search(@RequestParam String text) {
        return ApiResponse.success("25", "Publisher Search Completed Successfully",
                searchService.searchPublisher(text));
    }
}
