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
import self.learning.backend.lib.mgmt.dto.request.BookIssueCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.BookIssueUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.BookIssueResponseDto;
import self.learning.backend.lib.mgmt.service.CreateBookIssueService;
import self.learning.backend.lib.mgmt.service.DeleteBookIssueService;
import self.learning.backend.lib.mgmt.service.ReadBookIssueService;
import self.learning.backend.lib.mgmt.service.SearchBookIssueService;
import self.learning.backend.lib.mgmt.service.UpdateBookIssueService;
import self.learning.backend.lib.mgmt.service.presenter.PresenterBookIssueListService;

@RestController
@RequestMapping("/rest/issues")
public class BookIssueRestController {

    @Autowired private PresenterBookIssueListService presenterListService;
    @Autowired private CreateBookIssueService createService;
    @Autowired private ReadBookIssueService readService;
    @Autowired private UpdateBookIssueService updateService;
    @Autowired private DeleteBookIssueService deleteService;
    @Autowired private SearchBookIssueService searchService;

    @GetMapping
    public ApiResponse<List<BookIssueResponseDto>> list() {
        return ApiResponse.success(
                "P09",
                "Book Issue List Retrieved Successfully",
                presenterListService.listBookIssues());
    }

    @PostMapping
    public ApiResponse<BookIssueResponseDto> create(
            @RequestBody BookIssueCreateRequestDto request) {
        return ApiResponse.success(
                "41",
                "Book Issue Created Successfully",
                createService.createBookIssue(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<BookIssueResponseDto> read(@PathVariable Long id) {
        return ApiResponse.success(
                "42",
                "Book Issue Read Successfully",
                readService.readBookIssue(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<BookIssueResponseDto> update(
            @PathVariable Long id,
            @RequestBody BookIssueUpdateRequestDto request) {
        return ApiResponse.success(
                "43",
                "Book Issue Updated Successfully",
                updateService.updateBookIssue(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<BookIssueResponseDto> delete(@PathVariable Long id) {
        return ApiResponse.success(
                "44",
                "Book Issue Cancelled Successfully",
                deleteService.deleteBookIssue(id));
    }

    @GetMapping("/search")
    public ApiResponse<List<BookIssueResponseDto>> search(
            @RequestParam String text) {
        return ApiResponse.success(
                "45",
                "Book Issue Search Completed Successfully",
                searchService.searchBookIssue(text));
    }
}
