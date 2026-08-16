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

import self.learning.backend.lib.mgmt.dto.request.FineCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.FineUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.FineResponseDto;
import self.learning.backend.lib.mgmt.service.CreateFineService;
import self.learning.backend.lib.mgmt.service.DeleteFineService;
import self.learning.backend.lib.mgmt.service.SearchFineService;
import self.learning.backend.lib.mgmt.service.UpdateFineService;
import self.learning.backend.lib.mgmt.service.presenter.FineViewService;

@Controller
@RequestMapping("/library/fines")
public class FinePageController {

    @Autowired private FineViewService viewService;
    @Autowired private CreateFineService createService;
    @Autowired private UpdateFineService updateService;
    @Autowired private DeleteFineService deleteService;
    @Autowired private SearchFineService searchService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("records", viewService.findAll());
        return "fine/list";
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
                        : searchService.searchFine(text));
        return "fine/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        FineCreateRequestDto form = new FineCreateRequestDto();
        form.setStatus("OUTSTANDING");
        model.addAttribute("form", form);
        model.addAttribute("mode", "create");
        model.addAttribute("pageTitle", "Create Fine");
        return "fine/form";
    }

    @PostMapping
    public String create(
            @ModelAttribute("form") FineCreateRequestDto request,
            Model model) {
        FineResponseDto result = createService.createFine(request);
        if (result == null || result.getFineId() == null) {
            model.addAttribute("mode", "create");
            model.addAttribute("pageTitle", "Create Fine");
            model.addAttribute(
                    "message",
                    "The current Create service did not return a persisted Fine.");
            return "fine/form";
        }
        return "redirect:/library/fines/" + result.getFineId();
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("record", viewService.findById(id));
        model.addAttribute("moduleName", "Fine");
        model.addAttribute("listUrl", "/library/fines");
        return "fine/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        FineResponseDto record = viewService.findById(id);
        FineUpdateRequestDto form = new FineUpdateRequestDto();
        if (record != null) {
            form.setFineNumber(record.getFineNumber());
            form.setBookReturnId(record.getBookReturnId());
            form.setMembershipId(record.getMembershipId());
            form.setAmount(record.getAmount());
            form.setOutstandingAmount(record.getOutstandingAmount());
            form.setStatus(record.getStatus());
        }
        model.addAttribute("fineId", id);
        model.addAttribute("form", form);
        model.addAttribute("mode", "edit");
        model.addAttribute("pageTitle", "Update Fine");
        return "fine/form";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @ModelAttribute("form") FineUpdateRequestDto request,
            Model model) {
        FineResponseDto result = updateService.updateFine(id, request);
        if (result == null) {
            model.addAttribute("fineId", id);
            model.addAttribute("mode", "edit");
            model.addAttribute("pageTitle", "Update Fine");
            model.addAttribute(
                    "message",
                    "The current Update service did not return a Fine.");
            return "fine/form";
        }
        return "redirect:/library/fines/" + id;
    }

    @PostMapping("/{id}/delete")
    public String voidFine(@PathVariable Long id, Model model) {
        FineResponseDto result = deleteService.deleteFine(id);
        if (result == null) {
            model.addAttribute("record", viewService.findById(id));
            model.addAttribute("moduleName", "Fine");
            model.addAttribute("listUrl", "/library/fines");
            model.addAttribute(
                    "message",
                    "The current Void service did not return a Fine.");
            return "fine/detail";
        }
        return "redirect:/library/fines";
    }
}
