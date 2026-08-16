package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.ReservationCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.ReservationResponseDto;

public interface CreateReservationService {
    ReservationResponseDto createReservation(ReservationCreateRequestDto request);
}
