    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.FineResponseDto;
import self.learning.backend.lib.mgmt.service.UpdateFineService;
import self.learning.backend.lib.mgmt.dto.request.FineUpdateRequestDto;

    @Service
    public class UpdateFineServiceImpl implements UpdateFineService {
        @Override
    public FineResponseDto updateFine(Long id, FineUpdateRequestDto request) {
        FineResponseDto response = new FineResponseDto();
        response.setFineId(id);
        response.setFineNumber(request.getFineNumber());
        response.setBookReturnId(request.getBookReturnId());
        response.setMembershipId(request.getMembershipId());
        response.setAmount(request.getAmount());
        response.setOutstandingAmount(request.getOutstandingAmount());
        response.setStatus(request.getStatus());
        return response;
    }
    }
