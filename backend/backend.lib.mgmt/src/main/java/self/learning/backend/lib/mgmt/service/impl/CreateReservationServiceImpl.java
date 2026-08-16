    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.ReservationResponseDto;
import self.learning.backend.lib.mgmt.service.CreateReservationService;
import self.learning.backend.lib.mgmt.dto.request.ReservationCreateRequestDto;

    @Service
    public class CreateReservationServiceImpl implements CreateReservationService {
        @Override
    public ReservationResponseDto createReservation(ReservationCreateRequestDto request) {
        ReservationResponseDto response = new ReservationResponseDto();
        response.setReservationId(1001L);
        response.setReservationNumber(request.getReservationNumber());
        response.setMembershipId(request.getMembershipId());
        response.setBookId(request.getBookId());
        response.setReservedDate(request.getReservedDate());
        response.setStatus(request.getStatus());
        return response;
    }
    }
