package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.response.FineResponseDto;

public interface DeleteFineService {
    FineResponseDto deleteFine(Long id);
}
