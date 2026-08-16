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

import self.learning.backend.lib.mgmt.dto.request.BookIssueCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.BookIssueUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.BookIssueResponseDto;
import self.learning.backend.lib.mgmt.service.CreateBookIssueService;
import self.learning.backend.lib.mgmt.service.DeleteBookIssueService;
import self.learning.backend.lib.mgmt.service.SearchBookIssueService;
import self.learning.backend.lib.mgmt.service.UpdateBookIssueService;
import self.learning.backend.lib.mgmt.service.presenter.BookIssueViewService;

@Controller
@RequestMapping("/library/issues")
public class BookIssuePageController {

    @Autowired private BookIssueViewService viewService;
    @Autowired private CreateBookIssueService createService;
    @Autowired private UpdateBookIssueService updateService;
    @Autowired private DeleteBookIssueService deleteService;
    @Autowired private SearchBookIssueService searchService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("records", viewService.findAll());
        return "bookissue/list";
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
                        : searchService.searchBookIssue(text));
        return "bookissue/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookIssueCreateRequestDto());
        model.addAttribute("mode", "create");
        model.addAttribute("pageTitle", "Create Book Issue");
        return "bookissue/form";
    }

    @PostMapping
    public String create(
            @ModelAttribute("form") BookIssueCreateRequestDto request,
            Model model) {
        BookIssueResponseDto result = createService.createBookIssue(request);
        if (result == null || result.getBookIssueId() == null) {
            model.addAttribute("mode", "create");
            model.addAttribute("pageTitle", "Create Book Issue");
            model.addAttribute(
                    "message",
                    "The current Create service did not return a persisted Book Issue.");
            return "bookissue/form";
        }
        return "redirect:/library/issues/" + result.getBookIssueId();
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("record", viewService.findById(id));
        model.addAttribute("moduleName", "Book Issue");
        model.addAttribute("listUrl", "/library/issues");
        return "bookissue/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        BookIssueResponseDto record = viewService.findById(id);
        BookIssueUpdateRequestDto form = new BookIssueUpdateRequestDto();
        if (record != null) {
            form.setIssueNumber(record.getIssueNumber());
            form.setMembershipId(record.getMembershipId());
            form.setBookCopyId(record.getBookCopyId());
            form.setReservationId(record.getReservationId());
            form.setIssueDate(record.getIssueDate());
            form.setDueDate(record.getDueDate());
            form.setStatus(record.getStatus());
        }
        model.addAttribute("bookIssueId", id);
        model.addAttribute("form", form);
        model.addAttribute("mode", "edit");
        model.addAttribute("pageTitle", "Update Book Issue");
        return "bookissue/form";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @ModelAttribute("form") BookIssueUpdateRequestDto request,
            Model model) {
        BookIssueResponseDto result = updateService.updateBookIssue(id, request);
        if (result == null) {
            model.addAttribute("bookIssueId", id);
            model.addAttribute("mode", "edit");
            model.addAttribute("pageTitle", "Update Book Issue");
            model.addAttribute(
                    "message",
                    "The current Update service did not return a Book Issue.");
            return "bookissue/form";
        }
        return "redirect:/library/issues/" + id;
    }

    @PostMapping("/{id}/delete")
    public String cancel(@PathVariable Long id, Model model) {
        BookIssueResponseDto result = deleteService.deleteBookIssue(id);
        if (result == null) {
            model.addAttribute("record", viewService.findById(id));
            model.addAttribute("moduleName", "Book Issue");
            model.addAttribute("listUrl", "/library/issues");
            model.addAttribute(
                    "message",
                    "The current Cancel service did not return a Book Issue.");
            return "bookissue/detail";
        }
        return "redirect:/library/issues";
    }
}
