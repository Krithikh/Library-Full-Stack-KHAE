package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.FineUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.FineResponseDto;

public interface UpdateFineService {
    FineResponseDto updateFine(Long id, FineUpdateRequestDto request);
}
