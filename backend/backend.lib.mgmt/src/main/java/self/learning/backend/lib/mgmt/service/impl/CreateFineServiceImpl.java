    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.FineResponseDto;
import self.learning.backend.lib.mgmt.service.CreateFineService;
import self.learning.backend.lib.mgmt.dto.request.FineCreateRequestDto;

    @Service
    public class CreateFineServiceImpl implements CreateFineService {
        @Override
    public FineResponseDto createFine(FineCreateRequestDto request) {
        FineResponseDto response = new FineResponseDto();
        response.setFineId(1001L);
        response.setFineNumber(request.getFineNumber());
        response.setBookReturnId(request.getBookReturnId());
        response.setMembershipId(request.getMembershipId());
        response.setAmount(request.getAmount());
        response.setOutstandingAmount(request.getOutstandingAmount());
        response.setStatus(request.getStatus());
        return response;
    }
    }
