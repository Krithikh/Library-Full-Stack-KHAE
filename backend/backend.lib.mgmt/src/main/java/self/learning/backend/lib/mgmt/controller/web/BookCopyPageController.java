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

import self.learning.backend.lib.mgmt.dto.request.BookCopyCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.BookCopyUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.BookCopyResponseDto;
import self.learning.backend.lib.mgmt.service.CreateBookCopyService;
import self.learning.backend.lib.mgmt.service.DeleteBookCopyService;
import self.learning.backend.lib.mgmt.service.SearchBookCopyService;
import self.learning.backend.lib.mgmt.service.UpdateBookCopyService;
import self.learning.backend.lib.mgmt.service.presenter.BookCopyViewService;

@Controller
@RequestMapping("/library/book-copies")
public class BookCopyPageController {

    @Autowired private BookCopyViewService viewService;
    @Autowired private CreateBookCopyService createService;
    @Autowired private UpdateBookCopyService updateService;
    @Autowired private DeleteBookCopyService deleteService;
    @Autowired private SearchBookCopyService searchService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("records", viewService.findAll());
        return "bookcopy/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "") String text, Model model) {
        model.addAttribute("searchText", text);
        model.addAttribute("records", text.isBlank() ? viewService.findAll() : searchService.searchBookCopy(text));
        return "bookcopy/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookCopyCreateRequestDto());
        model.addAttribute("mode", "create");
        model.addAttribute("pageTitle", "Add Book Copy");
        return "bookcopy/form";
    }

    @PostMapping
    public String create(@ModelAttribute("form") BookCopyCreateRequestDto request, Model model) {
        BookCopyResponseDto result = createService.createBookCopy(request);
        if (result == null || result.getBookCopyId() == null) {
            model.addAttribute("mode", "create");
            model.addAttribute("pageTitle", "Add Book Copy");
            model.addAttribute("message", "The current Add service did not return a persisted Book Copy.");
            return "bookcopy/form";
        }
        return "redirect:/library/book-copies/" + result.getBookCopyId();
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("record", viewService.findById(id));
        model.addAttribute("moduleName", "Book Copy");
        model.addAttribute("listUrl", "/library/book-copies");
        return "bookcopy/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        BookCopyResponseDto record = viewService.findById(id);
        BookCopyUpdateRequestDto form = new BookCopyUpdateRequestDto();
        if (record != null) {
            form.setAccessionNumber(record.getAccessionNumber());
            form.setBookId(record.getBookId());
            form.setStatus(record.getStatus());
        }
        model.addAttribute("bookCopyId", id);
        model.addAttribute("form", form);
        model.addAttribute("mode", "edit");
        model.addAttribute("pageTitle", "Update Book Copy");
        return "bookcopy/form";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @ModelAttribute("form") BookCopyUpdateRequestDto request,
            Model model) {
        BookCopyResponseDto result = updateService.updateBookCopy(id, request);
        if (result == null) {
            model.addAttribute("bookCopyId", id);
            model.addAttribute("mode", "edit");
            model.addAttribute("pageTitle", "Update Book Copy");
            model.addAttribute("message", "The current Update service did not return a Book Copy.");
            return "bookcopy/form";
        }
        return "redirect:/library/book-copies/" + id;
    }

    @PostMapping("/{id}/delete")
    public String withdraw(@PathVariable Long id, Model model) {
        BookCopyResponseDto result = deleteService.deleteBookCopy(id);
        if (result == null) {
            model.addAttribute("record", viewService.findById(id));
            model.addAttribute("moduleName", "Book Copy");
            model.addAttribute("listUrl", "/library/book-copies");
            model.addAttribute("message", "The current Withdraw service did not return a Book Copy.");
            return "bookcopy/detail";
        }
        return "redirect:/library/book-copies";
    }
}
