    package self.learning.backend.lib.mgmt.service.impl;

    import java.util.List;
import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.ReservationResponseDto;
import self.learning.backend.lib.mgmt.service.SearchReservationService;
import java.time.LocalDate;

    @Service
    public class SearchReservationServiceImpl implements SearchReservationService {
        @Override
    public List<ReservationResponseDto> searchReservation(String text) {
        ReservationResponseDto response = new ReservationResponseDto();
        response.setReservationId(1L);
        response.setReservationNumber("RES-0001");
        response.setMembershipId(1L);
        response.setBookId(1L);
        response.setReservedDate(LocalDate.of(2026, 8, 1));
        response.setStatus("ACTIVE");
        return List.of(response);
    }
    }
