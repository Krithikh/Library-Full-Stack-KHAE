    package self.learning.backend.lib.mgmt.service.impl;

    import java.util.List;
import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.PublisherResponseDto;
import self.learning.backend.lib.mgmt.service.SearchPublisherService;

    @Service
    public class SearchPublisherServiceImpl implements SearchPublisherService {
        @Override
    public List<PublisherResponseDto> searchPublisher(String text) {
        PublisherResponseDto response = new PublisherResponseDto();
        response.setPublisherId(2L);
        response.setPublisherCode("PUB-ADDISON");
        response.setPublisherName("Addison-Wesley");
        response.setActive(true);
        return List.of(response);
    }
    }
