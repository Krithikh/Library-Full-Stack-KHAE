package self.learning.backend.lib.mgmt.service;

import java.util.List;
import self.learning.backend.lib.mgmt.dto.response.ReservationResponseDto;

public interface SearchReservationService {
    List<ReservationResponseDto> searchReservation(String text);
}
