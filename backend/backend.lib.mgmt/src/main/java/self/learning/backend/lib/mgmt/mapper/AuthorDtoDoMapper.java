package self.learning.backend.lib.mgmt.mapper;

import org.springframework.stereotype.Component;
import self.learning.backend.lib.mgmt.dataobject.AuthorDO;
import self.learning.backend.lib.mgmt.dto.request.AuthorCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.AuthorUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.AuthorResponseDto;

@Component
public class AuthorDtoDoMapper {

    public AuthorDO toDO(AuthorCreateRequestDto request) {
        AuthorDO dataObject = new AuthorDO();
        dataObject.setAuthorCode(request.getAuthorCode());
        dataObject.setAuthorName(request.getAuthorName());
        dataObject.setActive(true);
        return dataObject;
    }

    public void applyUpdate(AuthorUpdateRequestDto request, AuthorDO dataObject) {
        dataObject.setAuthorCode(request.getAuthorCode());
        dataObject.setAuthorName(request.getAuthorName());
    }

    public AuthorResponseDto toResponse(AuthorDO dataObject) {
        AuthorResponseDto response = new AuthorResponseDto();
        response.setAuthorId(dataObject.getAuthorId());
        response.setAuthorCode(dataObject.getAuthorCode());
        response.setAuthorName(dataObject.getAuthorName());
        response.setActive(dataObject.getActive());
        return response;
    }
}
