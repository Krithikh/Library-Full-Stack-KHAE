    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.PublisherResponseDto;
import self.learning.backend.lib.mgmt.service.CreatePublisherService;
import self.learning.backend.lib.mgmt.dto.request.PublisherCreateRequestDto;

    @Service
    public class CreatePublisherServiceImpl implements CreatePublisherService {
        @Override
    public PublisherResponseDto createPublisher(PublisherCreateRequestDto request) {
        PublisherResponseDto response = new PublisherResponseDto();
        response.setPublisherId(1001L);
        response.setPublisherCode(request.getPublisherCode());
        response.setPublisherName(request.getPublisherName());
        response.setActive(true);
        return response;
    }
    }
