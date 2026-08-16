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
import self.learning.backend.lib.mgmt.dto.request.MemberCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.MemberUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.MemberResponseDto;
import self.learning.backend.lib.mgmt.service.CreateMemberService;
import self.learning.backend.lib.mgmt.service.DeleteMemberService;
import self.learning.backend.lib.mgmt.service.ReadMemberService;
import self.learning.backend.lib.mgmt.service.SearchMemberService;
import self.learning.backend.lib.mgmt.service.UpdateMemberService;
import self.learning.backend.lib.mgmt.service.presenter.MemberViewService;

@RestController
@RequestMapping("/rest/members")
public class MemberRestController {

    @Autowired private MemberViewService viewService;
    @Autowired private CreateMemberService createService;
    @Autowired private ReadMemberService readService;
    @Autowired private UpdateMemberService updateService;
    @Autowired private DeleteMemberService deleteService;
    @Autowired private SearchMemberService searchService;

    /** Presenter-only support used by the Member list frontend before student services are integrated. */
    @GetMapping
    public ApiResponse<List<MemberResponseDto>> list() {
        return ApiResponse.success("P02", "Member List Retrieved Successfully", viewService.findAll());
    }

    @PostMapping
    public ApiResponse<MemberResponseDto> create(@RequestBody MemberCreateRequestDto request) {
        return ApiResponse.success("06", "Member Created Successfully", createService.createMember(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<MemberResponseDto> read(@PathVariable Long id) {
        return ApiResponse.success("07", "Member Read Successfully", readService.readMember(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<MemberResponseDto> update(@PathVariable Long id, @RequestBody MemberUpdateRequestDto request) {
        return ApiResponse.success("08", "Member Updated Successfully", updateService.updateMember(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<MemberResponseDto> delete(@PathVariable Long id) {
        return ApiResponse.success("09", "Member Deactivated Successfully", deleteService.deleteMember(id));
    }

    @GetMapping("/search")
    public ApiResponse<List<MemberResponseDto>> search(@RequestParam String text) {
        return ApiResponse.success("10", "Member Search Completed Successfully", searchService.searchMember(text));
    }
}
