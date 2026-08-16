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

import self.learning.backend.lib.mgmt.dto.request.BookReturnCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.BookReturnUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.BookReturnResponseDto;
import self.learning.backend.lib.mgmt.service.CreateBookReturnService;
import self.learning.backend.lib.mgmt.service.DeleteBookReturnService;
import self.learning.backend.lib.mgmt.service.SearchBookReturnService;
import self.learning.backend.lib.mgmt.service.UpdateBookReturnService;
import self.learning.backend.lib.mgmt.service.presenter.BookReturnViewService;

@Controller
@RequestMapping("/library/returns")
public class BookReturnPageController {

    @Autowired private BookReturnViewService viewService;
    @Autowired private CreateBookReturnService createService;
    @Autowired private UpdateBookReturnService updateService;
    @Autowired private DeleteBookReturnService deleteService;
    @Autowired private SearchBookReturnService searchService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("records", viewService.findAll());
        return "bookreturn/list";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam(defaultValue = "") String text,
            Model model) {
        model.addAttribute("searchText", text);
        model.addAttribute(
                "records",
                text.isBlank()
                        ? viewService.findAll()
                        : searchService.searchBookReturn(text));
        return "bookreturn/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        BookReturnCreateRequestDto form = new BookReturnCreateRequestDto();
        form.setStatus("COMPLETED");
        model.addAttribute("form", form);
        model.addAttribute("mode", "create");
        model.addAttribute("pageTitle", "Create Book Return");
        return "bookreturn/form";
    }

    @PostMapping
    public String create(
            @ModelAttribute("form") BookReturnCreateRequestDto request,
            Model model) {
        BookReturnResponseDto result = createService.createBookReturn(request);
        if (result == null || result.getBookReturnId() == null) {
            model.addAttribute("mode", "create");
            model.addAttribute("pageTitle", "Create Book Return");
            model.addAttribute(
                    "message",
                    "The current Create service did not return a persisted Book Return.");
            return "bookreturn/form";
        }
        return "redirect:/library/returns/" + result.getBookReturnId();
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("record", viewService.findById(id));
        model.addAttribute("moduleName", "Book Return");
        model.addAttribute("listUrl", "/library/returns");
        return "bookreturn/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        BookReturnResponseDto record = viewService.findById(id);
        BookReturnUpdateRequestDto form = new BookReturnUpdateRequestDto();
        if (record != null) {
            form.setReturnNumber(record.getReturnNumber());
            form.setBookIssueId(record.getBookIssueId());
            form.setReturnDate(record.getReturnDate());
            form.setStatus(record.getStatus());
        }
        model.addAttribute("bookReturnId", id);
        model.addAttribute("form", form);
        model.addAttribute("mode", "edit");
        model.addAttribute("pageTitle", "Update Book Return");
        return "bookreturn/form";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @ModelAttribute("form") BookReturnUpdateRequestDto request,
            Model model) {
        BookReturnResponseDto result = updateService.updateBookReturn(id, request);
        if (result == null) {
            model.addAttribute("bookReturnId", id);
            model.addAttribute("mode", "edit");
            model.addAttribute("pageTitle", "Update Book Return");
            model.addAttribute(
                    "message",
                    "The current Update service did not return a Book Return.");
            return "bookreturn/form";
        }
        return "redirect:/library/returns/" + id;
    }

    @PostMapping("/{id}/delete")
    public String voidReturn(@PathVariable Long id, Model model) {
        BookReturnResponseDto result = deleteService.deleteBookReturn(id);
        if (result == null) {
            model.addAttribute("record", viewService.findById(id));
            model.addAttribute("moduleName", "Book Return");
            model.addAttribute("listUrl", "/library/returns");
            model.addAttribute(
                    "message",
                    "The current Void service did not return a Book Return.");
            return "bookreturn/detail";
        }
        return "redirect:/library/returns";
    }
}
