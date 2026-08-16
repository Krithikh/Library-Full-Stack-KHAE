package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.ReservationUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.ReservationResponseDto;

public interface UpdateReservationService {
    ReservationResponseDto updateReservation(Long id, ReservationUpdateRequestDto request);
}
