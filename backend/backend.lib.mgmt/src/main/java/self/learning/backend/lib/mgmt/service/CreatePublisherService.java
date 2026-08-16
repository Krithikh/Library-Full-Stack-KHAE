package self.learning.backend.lib.mgmt.service;

import self.learning.backend.lib.mgmt.dto.request.PublisherCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.PublisherResponseDto;

public interface CreatePublisherService {
    PublisherResponseDto createPublisher(PublisherCreateRequestDto request);
}
