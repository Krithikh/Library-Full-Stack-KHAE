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
import self.learning.backend.lib.mgmt.dto.request.BookCopyCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.BookCopyUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.BookCopyResponseDto;
import self.learning.backend.lib.mgmt.service.CreateBookCopyService;
import self.learning.backend.lib.mgmt.service.ReadBookCopyService;
import self.learning.backend.lib.mgmt.service.UpdateBookCopyService;
import self.learning.backend.lib.mgmt.service.DeleteBookCopyService;
import self.learning.backend.lib.mgmt.service.SearchBookCopyService;
import self.learning.backend.lib.mgmt.service.presenter.PresenterBookCopyListService;

@RestController
@RequestMapping("/rest/book-copies")
public class BookCopyRestController {

    @Autowired private PresenterBookCopyListService presenterListService;
    @Autowired private CreateBookCopyService createService;
    @Autowired private ReadBookCopyService readService;
    @Autowired private UpdateBookCopyService updateService;
    @Autowired private DeleteBookCopyService deleteService;
    @Autowired private SearchBookCopyService searchService;

    @GetMapping
    public ApiResponse<List<BookCopyResponseDto>> list() {
        return ApiResponse.success("P07", "Book Copy List Retrieved Successfully",
                presenterListService.listBookCopies());
    }

    @PostMapping
    public ApiResponse<BookCopyResponseDto> create(@RequestBody BookCopyCreateRequestDto request) {
        return ApiResponse.success("31", "Book Copy Added Successfully",
                createService.createBookCopy(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<BookCopyResponseDto> read(@PathVariable Long id) {
        return ApiResponse.success("32", "Book Copy Read Successfully",
                readService.readBookCopy(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<BookCopyResponseDto> update(
            @PathVariable Long id,
            @RequestBody BookCopyUpdateRequestDto request) {
        return ApiResponse.success("33", "Book Copy Updated Successfully",
                updateService.updateBookCopy(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<BookCopyResponseDto> delete(@PathVariable Long id) {
        return ApiResponse.success("34", "Book Copy Withdrawn Successfully",
                deleteService.deleteBookCopy(id));
    }

    @GetMapping("/search")
    public ApiResponse<List<BookCopyResponseDto>> search(@RequestParam String text) {
        return ApiResponse.success("35", "Book Copy Search Completed Successfully",
                searchService.searchBookCopy(text));
    }
}
