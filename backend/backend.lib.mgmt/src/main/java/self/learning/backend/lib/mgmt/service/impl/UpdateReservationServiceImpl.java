    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.ReservationResponseDto;
import self.learning.backend.lib.mgmt.service.UpdateReservationService;
import self.learning.backend.lib.mgmt.dto.request.ReservationUpdateRequestDto;

    @Service
    public class UpdateReservationServiceImpl implements UpdateReservationService {
        @Override
    public ReservationResponseDto updateReservation(Long id, ReservationUpdateRequestDto request) {
        ReservationResponseDto response = new ReservationResponseDto();
        response.setReservationId(id);
        response.setReservationNumber(request.getReservationNumber());
        response.setMembershipId(request.getMembershipId());
        response.setBookId(request.getBookId());
        response.setReservedDate(request.getReservedDate());
        response.setStatus(request.getStatus());
        return response;
    }
    }
