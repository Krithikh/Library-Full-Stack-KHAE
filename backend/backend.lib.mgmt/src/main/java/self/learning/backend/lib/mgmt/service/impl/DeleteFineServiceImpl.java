    package self.learning.backend.lib.mgmt.service.impl;

    import org.springframework.stereotype.Service;
import self.learning.backend.lib.mgmt.dto.response.FineResponseDto;
import self.learning.backend.lib.mgmt.service.DeleteFineService;
import java.math.BigDecimal;

    @Service
    public class DeleteFineServiceImpl implements DeleteFineService {
        @Override
    public FineResponseDto deleteFine(Long id) {
        FineResponseDto response = new FineResponseDto();
        response.setFineId(1L);
        response.setFineNumber("FINE-0001");
        response.setBookReturnId(1L);
        response.setMembershipId(1L);
        response.setAmount(new BigDecimal("50.00"));
        response.setOutstandingAmount(new BigDecimal("25.00"));
        response.setStatus("OUTSTANDING");
        response.setFineId(id);
        response.setStatus("VOID");
        return response;
    }
    }
