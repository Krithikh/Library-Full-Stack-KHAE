package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.FineCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.FineResponseDto;

public interface CreateFineService {
    FineResponseDto createFine(FineCreateRequestDto request);
}
