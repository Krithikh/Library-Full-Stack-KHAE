package self.learning.backend.lib.mgmt.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import self.learning.backend.lib.mgmt.dto.request.MemberCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.MemberUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.MemberResponseDto;
import self.learning.backend.lib.mgmt.service.CreateMemberService;
import self.learning.backend.lib.mgmt.service.DeleteMemberService;
import self.learning.backend.lib.mgmt.service.SearchMemberService;
import self.learning.backend.lib.mgmt.service.UpdateMemberService;
import self.learning.backend.lib.mgmt.service.presenter.MemberViewService;

@Controller
@RequestMapping("/library/members")
public class MemberPageController {

    @Autowired private MemberViewService viewService;
    @Autowired private CreateMemberService createService;
    @Autowired private UpdateMemberService updateService;
    @Autowired private DeleteMemberService deleteService;
    @Autowired private SearchMemberService searchService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("records", viewService.findAll());
        model.addAttribute("searchText", "");
        return "member/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam String text, Model model) {
        List<MemberResponseDto> records = searchService.searchMember(text);
        model.addAttribute("records", records);
        model.addAttribute("searchText", text);
        return "member/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("form", new MemberCreateRequestDto());
        return "member/form-create";
    }

    @PostMapping
    public String create(@ModelAttribute("form") MemberCreateRequestDto request) {
        MemberResponseDto created = createService.createMember(request);
        return "redirect:/library/members/" + created.getMemberId();
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("record", viewService.findById(id));
        model.addAttribute("moduleName", "Member");
        model.addAttribute("listUrl", "/library/members");
        return "member/detail";
    }

    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable Long id, Model model) {
        MemberResponseDto current = viewService.findById(id);
        MemberUpdateRequestDto form = new MemberUpdateRequestDto();
        form.setRegistrationNumber(current.getRegistrationNumber());
        form.setFullName(current.getFullName());
        form.setEmail(current.getEmail());
        form.setDepartmentId(current.getDepartmentId());
        model.addAttribute("memberId", id);
        model.addAttribute("form", form);
        return "member/form-update";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute("form") MemberUpdateRequestDto request) {
        updateService.updateMember(id, request);
        return "redirect:/library/members/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        deleteService.deleteMember(id);
        return "redirect:/library/members";
    }
}
