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
import self.learning.backend.lib.mgmt.dto.request.MembershipCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.MembershipUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.MembershipResponseDto;
import self.learning.backend.lib.mgmt.service.CreateMembershipService;
import self.learning.backend.lib.mgmt.service.DeleteMembershipService;
import self.learning.backend.lib.mgmt.service.ReadMembershipService;
import self.learning.backend.lib.mgmt.service.SearchMembershipService;
import self.learning.backend.lib.mgmt.service.UpdateMembershipService;
import self.learning.backend.lib.mgmt.service.presenter.PresenterMembershipListService;

@RestController
@RequestMapping("/rest/memberships")
public class MembershipRestController {

    @Autowired private PresenterMembershipListService presenterListService;
    @Autowired private CreateMembershipService createService;
    @Autowired private ReadMembershipService readService;
    @Autowired private UpdateMembershipService updateService;
    @Autowired private DeleteMembershipService deleteService;
    @Autowired private SearchMembershipService searchService;

    @GetMapping
    public ApiResponse<List<MembershipResponseDto>> list() {
        return ApiResponse.success("P08", "Membership List Retrieved Successfully",
                presenterListService.listMemberships());
    }

    @PostMapping
    public ApiResponse<MembershipResponseDto> create(@RequestBody MembershipCreateRequestDto request) {
        return ApiResponse.success("36", "Membership Created Successfully",
                createService.createMembership(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<MembershipResponseDto> read(@PathVariable Long id) {
        return ApiResponse.success("37", "Membership Read Successfully",
                readService.readMembership(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<MembershipResponseDto> update(
            @PathVariable Long id,
            @RequestBody MembershipUpdateRequestDto request) {
        return ApiResponse.success("38", "Membership Updated Successfully",
                updateService.updateMembership(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<MembershipResponseDto> delete(@PathVariable Long id) {
        return ApiResponse.success("39", "Membership Deactivated Successfully",
                deleteService.deleteMembership(id));
    }

    @GetMapping("/search")
    public ApiResponse<List<MembershipResponseDto>> search(@RequestParam String text) {
        return ApiResponse.success("40", "Membership Search Completed Successfully",
                searchService.searchMembership(text));
    }
}
