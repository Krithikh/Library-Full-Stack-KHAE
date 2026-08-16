package self.learning.backend.lib.mgmt.service;

import java.util.List;
import self.learning.backend.lib.mgmt.dto.response.FineResponseDto;

public interface SearchFineService {
    List<FineResponseDto> searchFine(String text);
}
