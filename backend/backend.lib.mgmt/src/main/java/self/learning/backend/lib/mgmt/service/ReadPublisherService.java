package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.response.PublisherResponseDto;

public interface ReadPublisherService {
    PublisherResponseDto readPublisher(Long id);
}
