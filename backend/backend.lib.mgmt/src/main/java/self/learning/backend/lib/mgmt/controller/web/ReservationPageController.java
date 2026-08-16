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

import self.learning.backend.lib.mgmt.dto.request.ReservationCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.ReservationUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.ReservationResponseDto;
import self.learning.backend.lib.mgmt.service.CreateReservationService;
import self.learning.backend.lib.mgmt.service.DeleteReservationService;
import self.learning.backend.lib.mgmt.service.SearchReservationService;
import self.learning.backend.lib.mgmt.service.UpdateReservationService;
import self.learning.backend.lib.mgmt.service.presenter.ReservationViewService;

@Controller
@RequestMapping("/library/reservations")
public class ReservationPageController {

    @Autowired private ReservationViewService viewService;
    @Autowired private CreateReservationService createService;
    @Autowired private UpdateReservationService updateService;
    @Autowired private DeleteReservationService deleteService;
    @Autowired private SearchReservationService searchService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("records", viewService.findAll());
        return "reservation/list";
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
                        : searchService.searchReservation(text));
        return "reservation/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        ReservationCreateRequestDto form = new ReservationCreateRequestDto();
        form.setStatus("ACTIVE");
        model.addAttribute("form", form);
        model.addAttribute("mode", "create");
        model.addAttribute("pageTitle", "Create Reservation");
        return "reservation/form";
    }

    @PostMapping
    public String create(
            @ModelAttribute("form") ReservationCreateRequestDto request,
            Model model) {
        ReservationResponseDto result = createService.createReservation(request);
        if (result == null || result.getReservationId() == null) {
            model.addAttribute("mode", "create");
            model.addAttribute("pageTitle", "Create Reservation");
            model.addAttribute(
                    "message",
                    "The current Create service did not return a persisted Reservation.");
            return "reservation/form";
        }
        return "redirect:/library/reservations/" + result.getReservationId();
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("record", viewService.findById(id));
        model.addAttribute("moduleName", "Reservation");
        model.addAttribute("listUrl", "/library/reservations");
        return "reservation/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        ReservationResponseDto record = viewService.findById(id);
        ReservationUpdateRequestDto form = new ReservationUpdateRequestDto();
        if (record != null) {
            form.setReservationNumber(record.getReservationNumber());
            form.setMembershipId(record.getMembershipId());
            form.setBookId(record.getBookId());
            form.setReservedDate(record.getReservedDate());
            form.setStatus(record.getStatus());
        }
        model.addAttribute("reservationId", id);
        model.addAttribute("form", form);
        model.addAttribute("mode", "edit");
        model.addAttribute("pageTitle", "Update Reservation");
        return "reservation/form";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @ModelAttribute("form") ReservationUpdateRequestDto request,
            Model model) {
        ReservationResponseDto result = updateService.updateReservation(id, request);
        if (result == null) {
            model.addAttribute("reservationId", id);
            model.addAttribute("mode", "edit");
            model.addAttribute("pageTitle", "Update Reservation");
            model.addAttribute(
                    "message",
                    "The current Update service did not return a Reservation.");
            return "reservation/form";
        }
        return "redirect:/library/reservations/" + id;
    }

    @PostMapping("/{id}/delete")
    public String cancel(@PathVariable Long id, Model model) {
        ReservationResponseDto result = deleteService.deleteReservation(id);
        if (result == null) {
            model.addAttribute("record", viewService.findById(id));
            model.addAttribute("moduleName", "Reservation");
            model.addAttribute("listUrl", "/library/reservations");
            model.addAttribute(
                    "message",
                    "The current Cancel service did not return a Reservation.");
            return "reservation/detail";
        }
        return "redirect:/library/reservations";
    }
}
