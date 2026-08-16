package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.response.ReservationResponseDto;

public interface DeleteReservationService {
    ReservationResponseDto deleteReservation(Long id);
}
