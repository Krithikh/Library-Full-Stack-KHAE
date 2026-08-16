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
import self.learning.backend.lib.mgmt.dto.request.ReservationCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.ReservationUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.ReservationResponseDto;
import self.learning.backend.lib.mgmt.service.CreateReservationService;
import self.learning.backend.lib.mgmt.service.DeleteReservationService;
import self.learning.backend.lib.mgmt.service.ReadReservationService;
import self.learning.backend.lib.mgmt.service.SearchReservationService;
import self.learning.backend.lib.mgmt.service.UpdateReservationService;
import self.learning.backend.lib.mgmt.service.presenter.PresenterReservationListService;

@RestController
@RequestMapping("/rest/reservations")
public class ReservationRestController {

    @Autowired private PresenterReservationListService presenterListService;
    @Autowired private CreateReservationService createService;
    @Autowired private ReadReservationService readService;
    @Autowired private UpdateReservationService updateService;
    @Autowired private DeleteReservationService deleteService;
    @Autowired private SearchReservationService searchService;

    @GetMapping
    public ApiResponse<List<ReservationResponseDto>> list() {
        return ApiResponse.success(
                "P11",
                "Reservation List Retrieved Successfully",
                presenterListService.listReservations());
    }

    @PostMapping
    public ApiResponse<ReservationResponseDto> create(
            @RequestBody ReservationCreateRequestDto request) {
        return ApiResponse.success(
                "51",
                "Reservation Created Successfully",
                createService.createReservation(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<ReservationResponseDto> read(@PathVariable Long id) {
        return ApiResponse.success(
                "52",
                "Reservation Read Successfully",
                readService.readReservation(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<ReservationResponseDto> update(
            @PathVariable Long id,
            @RequestBody ReservationUpdateRequestDto request) {
        return ApiResponse.success(
                "53",
                "Reservation Updated Successfully",
                updateService.updateReservation(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<ReservationResponseDto> delete(@PathVariable Long id) {
        return ApiResponse.success(
                "54",
                "Reservation Cancelled Successfully",
                deleteService.deleteReservation(id));
    }

    @GetMapping("/search")
    public ApiResponse<List<ReservationResponseDto>> search(
            @RequestParam String text) {
        return ApiResponse.success(
                "55",
                "Reservation Search Completed Successfully",
                searchService.searchReservation(text));
    }
}
