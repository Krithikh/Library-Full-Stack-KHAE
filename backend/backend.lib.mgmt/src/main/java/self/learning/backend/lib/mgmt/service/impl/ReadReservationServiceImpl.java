    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.ReservationResponseDto;
import self.learning.backend.lib.mgmt.service.ReadReservationService;
import java.time.LocalDate;

    @Service
    public class ReadReservationServiceImpl implements ReadReservationService {
        @Override
    public ReservationResponseDto readReservation(Long id) {
        ReservationResponseDto response = new ReservationResponseDto();
        response.setReservationId(1L);
        response.setReservationNumber("RES-0001");
        response.setMembershipId(1L);
        response.setBookId(1L);
        response.setReservedDate(LocalDate.of(2026, 8, 1));
        response.setStatus("ACTIVE");
        response.setReservationId(id);
        return response;
    }
    }
