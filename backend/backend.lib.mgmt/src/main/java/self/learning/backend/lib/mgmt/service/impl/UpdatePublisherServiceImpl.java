    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.PublisherResponseDto;
import self.learning.backend.lib.mgmt.service.UpdatePublisherService;
import self.learning.backend.lib.mgmt.dto.request.PublisherUpdateRequestDto;

    @Service
    public class UpdatePublisherServiceImpl implements UpdatePublisherService {
        @Override
    public PublisherResponseDto updatePublisher(Long id, PublisherUpdateRequestDto request) {
        PublisherResponseDto response = new PublisherResponseDto();
        response.setPublisherId(id);
        response.setPublisherCode(request.getPublisherCode());
        response.setPublisherName(request.getPublisherName());
        response.setActive(true);
        return response;
    }
    }
