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
import self.learning.backend.lib.mgmt.dto.request.BookCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.BookUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.BookResponseDto;
import self.learning.backend.lib.mgmt.service.CreateBookService;
import self.learning.backend.lib.mgmt.service.DeleteBookService;
import self.learning.backend.lib.mgmt.service.ReadBookService;
import self.learning.backend.lib.mgmt.service.SearchBookService;
import self.learning.backend.lib.mgmt.service.UpdateBookService;
import self.learning.backend.lib.mgmt.service.presenter.PresenterBookListService;

@RestController
@RequestMapping("/rest/books")
public class BookRestController {

    @Autowired private PresenterBookListService presenterListService;
    @Autowired private CreateBookService createService;
    @Autowired private ReadBookService readService;
    @Autowired private UpdateBookService updateService;
    @Autowired private DeleteBookService deleteService;
    @Autowired private SearchBookService searchService;

    @GetMapping
    public ApiResponse<List<BookResponseDto>> list() {
        return ApiResponse.success("P06", "Book List Retrieved Successfully",
                presenterListService.listBooks());
    }

    @PostMapping
    public ApiResponse<BookResponseDto> create(@RequestBody BookCreateRequestDto request) {
        return ApiResponse.success("26", "Book Created Successfully",
                createService.createBook(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<BookResponseDto> read(@PathVariable Long id) {
        return ApiResponse.success("27", "Book Read Successfully",
                readService.readBook(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<BookResponseDto> update(
            @PathVariable Long id,
            @RequestBody BookUpdateRequestDto request) {
        return ApiResponse.success("28", "Book Updated Successfully",
                updateService.updateBook(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<BookResponseDto> delete(@PathVariable Long id) {
        return ApiResponse.success("29", "Book Deactivated Successfully",
                deleteService.deleteBook(id));
    }

    @GetMapping("/search")
    public ApiResponse<List<BookResponseDto>> search(@RequestParam String text) {
        return ApiResponse.success("30", "Book Search Completed Successfully",
                searchService.searchBook(text));
    }
}
