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

import self.learning.backend.lib.mgmt.dto.request.MembershipCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.MembershipUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.MembershipResponseDto;
import self.learning.backend.lib.mgmt.service.CreateMembershipService;
import self.learning.backend.lib.mgmt.service.DeleteMembershipService;
import self.learning.backend.lib.mgmt.service.SearchMembershipService;
import self.learning.backend.lib.mgmt.service.UpdateMembershipService;
import self.learning.backend.lib.mgmt.service.presenter.MembershipViewService;

@Controller
@RequestMapping("/library/memberships")
public class MembershipPageController {

    @Autowired private MembershipViewService viewService;
    @Autowired private CreateMembershipService createService;
    @Autowired private UpdateMembershipService updateService;
    @Autowired private DeleteMembershipService deleteService;
    @Autowired private SearchMembershipService searchService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("records", viewService.findAll());
        return "membership/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "") String text, Model model) {
        model.addAttribute("searchText", text);
        model.addAttribute("records", text.isBlank() ? viewService.findAll() : searchService.searchMembership(text));
        return "membership/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("form", new MembershipCreateRequestDto());
        model.addAttribute("mode", "create");
        model.addAttribute("pageTitle", "Create Membership");
        return "membership/form";
    }

    @PostMapping
    public String create(@ModelAttribute("form") MembershipCreateRequestDto request, Model model) {
        MembershipResponseDto result = createService.createMembership(request);
        if (result == null || result.getMembershipId() == null) {
            model.addAttribute("mode", "create");
            model.addAttribute("pageTitle", "Create Membership");
            model.addAttribute("message", "The current Create service did not return a persisted Membership.");
            return "membership/form";
        }
        return "redirect:/library/memberships/" + result.getMembershipId();
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("record", viewService.findById(id));
        model.addAttribute("moduleName", "Membership");
        model.addAttribute("listUrl", "/library/memberships");
        return "membership/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        MembershipResponseDto record = viewService.findById(id);
        MembershipUpdateRequestDto form = new MembershipUpdateRequestDto();
        if (record != null) {
            form.setMembershipNumber(record.getMembershipNumber());
            form.setMemberId(record.getMemberId());
            form.setMembershipType(record.getMembershipType());
            form.setStatus(record.getStatus());
        }
        model.addAttribute("membershipId", id);
        model.addAttribute("form", form);
        model.addAttribute("mode", "edit");
        model.addAttribute("pageTitle", "Update Membership");
        return "membership/form";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @ModelAttribute("form") MembershipUpdateRequestDto request,
            Model model) {
        MembershipResponseDto result = updateService.updateMembership(id, request);
        if (result == null) {
            model.addAttribute("membershipId", id);
            model.addAttribute("mode", "edit");
            model.addAttribute("pageTitle", "Update Membership");
            model.addAttribute("message", "The current Update service did not return a Membership.");
            return "membership/form";
        }
        return "redirect:/library/memberships/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deactivate(@PathVariable Long id, Model model) {
        MembershipResponseDto result = deleteService.deleteMembership(id);
        if (result == null) {
            model.addAttribute("record", viewService.findById(id));
            model.addAttribute("moduleName", "Membership");
            model.addAttribute("listUrl", "/library/memberships");
            model.addAttribute("message", "The current Deactivate service did not return a Membership.");
            return "membership/detail";
        }
        return "redirect:/library/memberships";
    }
}
