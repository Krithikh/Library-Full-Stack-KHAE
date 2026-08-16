package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.PublisherUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.PublisherResponseDto;

public interface UpdatePublisherService {
    PublisherResponseDto updatePublisher(Long id, PublisherUpdateRequestDto request);
}
