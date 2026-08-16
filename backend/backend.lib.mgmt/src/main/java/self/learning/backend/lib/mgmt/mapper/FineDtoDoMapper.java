package self.learning.backend.lib.mgmt.mapper;

import org.springframework.stereotype.Component;
import self.learning.backend.lib.mgmt.dataobject.FineDO;
import self.learning.backend.lib.mgmt.dto.request.FineCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.FineUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.FineResponseDto;

@Component
public class FineDtoDoMapper {

    public FineDO toDO(FineCreateRequestDto request) {
        FineDO dataObject = new FineDO();
        dataObject.setFineNumber(request.getFineNumber());
        dataObject.setBookReturnId(request.getBookReturnId());
        dataObject.setMembershipId(request.getMembershipId());
        dataObject.setAmount(request.getAmount());
        dataObject.setOutstandingAmount(request.getOutstandingAmount());
        dataObject.setStatus(request.getStatus());
        return dataObject;
    }

    public void applyUpdate(FineUpdateRequestDto request, FineDO dataObject) {
        dataObject.setFineNumber(request.getFineNumber());
        dataObject.setBookReturnId(request.getBookReturnId());
        dataObject.setMembershipId(request.getMembershipId());
        dataObject.setAmount(request.getAmount());
        dataObject.setOutstandingAmount(request.getOutstandingAmount());
        dataObject.setStatus(request.getStatus());
    }

    public FineResponseDto toResponse(FineDO dataObject) {
        FineResponseDto response = new FineResponseDto();
        response.setFineId(dataObject.getFineId());
        response.setFineNumber(dataObject.getFineNumber());
        response.setBookReturnId(dataObject.getBookReturnId());
        response.setMembershipId(dataObject.getMembershipId());
        response.setAmount(dataObject.getAmount());
        response.setOutstandingAmount(dataObject.getOutstandingAmount());
        response.setStatus(dataObject.getStatus());
        return response;
    }
}
