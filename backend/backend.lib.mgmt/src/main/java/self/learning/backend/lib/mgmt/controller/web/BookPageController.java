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

import self.learning.backend.lib.mgmt.dto.request.BookCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.BookUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.BookResponseDto;
import self.learning.backend.lib.mgmt.service.CreateBookService;
import self.learning.backend.lib.mgmt.service.DeleteBookService;
import self.learning.backend.lib.mgmt.service.SearchBookService;
import self.learning.backend.lib.mgmt.service.UpdateBookService;
import self.learning.backend.lib.mgmt.service.presenter.BookViewService;

@Controller
@RequestMapping("/library/books")
public class BookPageController {

    @Autowired private BookViewService viewService;
    @Autowired private CreateBookService createService;
    @Autowired private UpdateBookService updateService;
    @Autowired private DeleteBookService deleteService;
    @Autowired private SearchBookService searchService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("records", viewService.findAll());
        return "book/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "") String text, Model model) {
        model.addAttribute("searchText", text);
        model.addAttribute("records", text.isBlank() ? viewService.findAll() : searchService.searchBook(text));
        return "book/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookCreateRequestDto());
        model.addAttribute("mode", "create");
        model.addAttribute("pageTitle", "Create Book");
        return "book/form";
    }

    @PostMapping
    public String create(@ModelAttribute("form") BookCreateRequestDto request, Model model) {
        BookResponseDto result = createService.createBook(request);
        if (result == null || result.getBookId() == null) {
            model.addAttribute("mode", "create");
            model.addAttribute("pageTitle", "Create Book");
            model.addAttribute("message", "The current Create service did not return a persisted Book.");
            return "book/form";
        }
        return "redirect:/library/books/" + result.getBookId();
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("record", viewService.findById(id));
        model.addAttribute("moduleName", "Book");
        model.addAttribute("listUrl", "/library/books");
        return "book/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        BookResponseDto record = viewService.findById(id);
        BookUpdateRequestDto form = new BookUpdateRequestDto();
        if (record != null) {
            form.setIsbn(record.getIsbn());
            form.setTitle(record.getTitle());
            form.setAuthorId(record.getAuthorId());
            form.setCategoryId(record.getCategoryId());
            form.setPublisherId(record.getPublisherId());
        }
        model.addAttribute("bookId", id);
        model.addAttribute("form", form);
        model.addAttribute("mode", "edit");
        model.addAttribute("pageTitle", "Update Book");
        return "book/form";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @ModelAttribute("form") BookUpdateRequestDto request,
            Model model) {
        BookResponseDto result = updateService.updateBook(id, request);
        if (result == null) {
            model.addAttribute("bookId", id);
            model.addAttribute("mode", "edit");
            model.addAttribute("pageTitle", "Update Book");
            model.addAttribute("message", "The current Update service did not return a Book.");
            return "book/form";
        }
        return "redirect:/library/books/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deactivate(@PathVariable Long id, Model model) {
        BookResponseDto result = deleteService.deleteBook(id);
        if (result == null) {
            model.addAttribute("record", viewService.findById(id));
            model.addAttribute("moduleName", "Book");
            model.addAttribute("listUrl", "/library/books");
            model.addAttribute("message", "The current Deactivate service did not return a Book.");
            return "book/detail";
        }
        return "redirect:/library/books";
    }
}
