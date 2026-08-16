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

import self.learning.backend.lib.mgmt.dto.request.PublisherCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.PublisherUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.PublisherResponseDto;
import self.learning.backend.lib.mgmt.service.CreatePublisherService;
import self.learning.backend.lib.mgmt.service.DeletePublisherService;
import self.learning.backend.lib.mgmt.service.SearchPublisherService;
import self.learning.backend.lib.mgmt.service.UpdatePublisherService;
import self.learning.backend.lib.mgmt.service.presenter.PublisherViewService;

@Controller
@RequestMapping("/library/publishers")
public class PublisherPageController {

    @Autowired private PublisherViewService viewService;
    @Autowired private CreatePublisherService createService;
    @Autowired private UpdatePublisherService updateService;
    @Autowired private DeletePublisherService deleteService;
    @Autowired private SearchPublisherService searchService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("records", viewService.findAll());
        return "publisher/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "") String text, Model model) {
        model.addAttribute("searchText", text);
        model.addAttribute("records", text.isBlank() ? viewService.findAll() : searchService.searchPublisher(text));
        return "publisher/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("form", new PublisherCreateRequestDto());
        model.addAttribute("mode", "create");
        model.addAttribute("pageTitle", "Create Publisher");
        return "publisher/form";
    }

    @PostMapping
    public String create(@ModelAttribute("form") PublisherCreateRequestDto request, Model model) {
        PublisherResponseDto result = createService.createPublisher(request);
        if (result == null || result.getPublisherId() == null) {
            model.addAttribute("mode", "create");
            model.addAttribute("pageTitle", "Create Publisher");
            model.addAttribute("message", "The current Create service did not return a persisted Publisher.");
            return "publisher/form";
        }
        return "redirect:/library/publishers/" + result.getPublisherId();
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("record", viewService.findById(id));
        model.addAttribute("moduleName", "Publisher");
        model.addAttribute("listUrl", "/library/publishers");
        return "publisher/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        PublisherResponseDto record = viewService.findById(id);
        PublisherUpdateRequestDto form = new PublisherUpdateRequestDto();
        if (record != null) {
            form.setPublisherCode(record.getPublisherCode());
            form.setPublisherName(record.getPublisherName());
        }
        model.addAttribute("publisherId", id);
        model.addAttribute("form", form);
        model.addAttribute("mode", "edit");
        model.addAttribute("pageTitle", "Update Publisher");
        return "publisher/form";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @ModelAttribute("form") PublisherUpdateRequestDto request,
            Model model) {
        PublisherResponseDto result = updateService.updatePublisher(id, request);
        if (result == null) {
            model.addAttribute("publisherId", id);
            model.addAttribute("mode", "edit");
            model.addAttribute("pageTitle", "Update Publisher");
            model.addAttribute("message", "The current Update service did not return a Publisher.");
            return "publisher/form";
        }
        return "redirect:/library/publishers/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deactivate(@PathVariable Long id, Model model) {
        PublisherResponseDto result = deleteService.deletePublisher(id);
        if (result == null) {
            model.addAttribute("record", viewService.findById(id));
            model.addAttribute("moduleName", "Publisher");
            model.addAttribute("listUrl", "/library/publishers");
            model.addAttribute("message", "The current Deactivate service did not return a Publisher.");
            return "publisher/detail";
        }
        return "redirect:/library/publishers";
    }
}
