package self.learning.backend.lib.mgmt.service;

import java.util.List;
import self.learning.backend.lib.mgmt.dto.response.PublisherResponseDto;

public interface SearchPublisherService {
    List<PublisherResponseDto> searchPublisher(String text);
}
