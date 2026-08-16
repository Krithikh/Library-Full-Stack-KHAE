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
import self.learning.backend.lib.mgmt.dto.request.AuthorCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.AuthorUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.AuthorResponseDto;
import self.learning.backend.lib.mgmt.service.CreateAuthorService;
import self.learning.backend.lib.mgmt.service.DeleteAuthorService;
import self.learning.backend.lib.mgmt.service.ReadAuthorService;
import self.learning.backend.lib.mgmt.service.SearchAuthorService;
import self.learning.backend.lib.mgmt.service.UpdateAuthorService;
import self.learning.backend.lib.mgmt.service.presenter.PresenterAuthorListService;

@RestController
@RequestMapping("/rest/authors")
public class AuthorRestController {

    @Autowired private PresenterAuthorListService presenterListService;
    @Autowired private CreateAuthorService createService;
    @Autowired private ReadAuthorService readService;
    @Autowired private UpdateAuthorService updateService;
    @Autowired private DeleteAuthorService deleteService;
    @Autowired private SearchAuthorService searchService;

    @GetMapping
    public ApiResponse<List<AuthorResponseDto>> list() {
        return ApiResponse.success("P03", "Author List Retrieved Successfully",
                presenterListService.listAuthors());
    }

    @PostMapping
    public ApiResponse<AuthorResponseDto> create(@RequestBody AuthorCreateRequestDto request) {
        return ApiResponse.success("11", "Author Created Successfully",
                createService.createAuthor(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<AuthorResponseDto> read(@PathVariable Long id) {
        return ApiResponse.success("12", "Author Read Successfully",
                readService.readAuthor(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<AuthorResponseDto> update(
            @PathVariable Long id,
            @RequestBody AuthorUpdateRequestDto request) {
        return ApiResponse.success("13", "Author Updated Successfully",
                updateService.updateAuthor(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<AuthorResponseDto> delete(@PathVariable Long id) {
        return ApiResponse.success("14", "Author Deactivated Successfully",
                deleteService.deleteAuthor(id));
    }

    @GetMapping("/search")
    public ApiResponse<List<AuthorResponseDto>> search(@RequestParam String text) {
        return ApiResponse.success("15", "Author Search Completed Successfully",
                searchService.searchAuthor(text));
    }
}
