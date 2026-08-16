    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.PublisherResponseDto;
import self.learning.backend.lib.mgmt.service.ReadPublisherService;

    @Service
    public class ReadPublisherServiceImpl implements ReadPublisherService {
        @Override
    public PublisherResponseDto readPublisher(Long id) {
        PublisherResponseDto response = new PublisherResponseDto();
        response.setPublisherId(2L);
        response.setPublisherCode("PUB-ADDISON");
        response.setPublisherName("Addison-Wesley");
        response.setActive(true);
        response.setPublisherId(id);
        return response;
    }
    }
