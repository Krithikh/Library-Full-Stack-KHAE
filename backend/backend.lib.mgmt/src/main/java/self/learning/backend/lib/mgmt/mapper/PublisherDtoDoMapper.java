package self.learning.backend.lib.mgmt.mapper;

import org.springframework.stereotype.Component;
import self.learning.backend.lib.mgmt.dataobject.PublisherDO;
import self.learning.backend.lib.mgmt.dto.request.PublisherCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.PublisherUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.PublisherResponseDto;

@Component
public class PublisherDtoDoMapper {

    public PublisherDO toDO(PublisherCreateRequestDto request) {
        PublisherDO dataObject = new PublisherDO();
        dataObject.setPublisherCode(request.getPublisherCode());
        dataObject.setPublisherName(request.getPublisherName());
        dataObject.setActive(true);
        return dataObject;
    }

    public void applyUpdate(PublisherUpdateRequestDto request, PublisherDO dataObject) {
        dataObject.setPublisherCode(request.getPublisherCode());
        dataObject.setPublisherName(request.getPublisherName());
    }

    public PublisherResponseDto toResponse(PublisherDO dataObject) {
        PublisherResponseDto response = new PublisherResponseDto();
        response.setPublisherId(dataObject.getPublisherId());
        response.setPublisherCode(dataObject.getPublisherCode());
        response.setPublisherName(dataObject.getPublisherName());
        response.setActive(dataObject.getActive());
        return response;
    }
}
