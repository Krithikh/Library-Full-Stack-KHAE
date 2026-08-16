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
import self.learning.backend.lib.mgmt.dto.request.BookReturnCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.BookReturnUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.BookReturnResponseDto;
import self.learning.backend.lib.mgmt.service.CreateBookReturnService;
import self.learning.backend.lib.mgmt.service.DeleteBookReturnService;
import self.learning.backend.lib.mgmt.service.ReadBookReturnService;
import self.learning.backend.lib.mgmt.service.SearchBookReturnService;
import self.learning.backend.lib.mgmt.service.UpdateBookReturnService;
import self.learning.backend.lib.mgmt.service.presenter.PresenterBookReturnListService;

@RestController
@RequestMapping("/rest/returns")
public class BookReturnRestController {

    @Autowired private PresenterBookReturnListService presenterListService;
    @Autowired private CreateBookReturnService createService;
    @Autowired private ReadBookReturnService readService;
    @Autowired private UpdateBookReturnService updateService;
    @Autowired private DeleteBookReturnService deleteService;
    @Autowired private SearchBookReturnService searchService;

    @GetMapping
    public ApiResponse<List<BookReturnResponseDto>> list() {
        return ApiResponse.success(
                "P10",
                "Book Return List Retrieved Successfully",
                presenterListService.listBookReturns());
    }

    @PostMapping
    public ApiResponse<BookReturnResponseDto> create(
            @RequestBody BookReturnCreateRequestDto request) {
        return ApiResponse.success(
                "46",
                "Book Return Created Successfully",
                createService.createBookReturn(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<BookReturnResponseDto> read(@PathVariable Long id) {
        return ApiResponse.success(
                "47",
                "Book Return Read Successfully",
                readService.readBookReturn(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<BookReturnResponseDto> update(
            @PathVariable Long id,
            @RequestBody BookReturnUpdateRequestDto request) {
        return ApiResponse.success(
                "48",
                "Book Return Updated Successfully",
                updateService.updateBookReturn(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<BookReturnResponseDto> delete(@PathVariable Long id) {
        return ApiResponse.success(
                "49",
                "Book Return Voided Successfully",
                deleteService.deleteBookReturn(id));
    }

    @GetMapping("/search")
    public ApiResponse<List<BookReturnResponseDto>> search(
            @RequestParam String text) {
        return ApiResponse.success(
                "50",
                "Book Return Search Completed Successfully",
                searchService.searchBookReturn(text));
    }
}
