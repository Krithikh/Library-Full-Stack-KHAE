package self.learning.backend.lib.mgmt.service.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import self.learning.backend.lib.mgmt.dao.FineDao;
import self.learning.backend.lib.mgmt.dto.response.FineResponseDto;
import self.learning.backend.lib.mgmt.mapper.FineDtoDoMapper;

@Service
public class PresenterFineListService {

    @Autowired
    private FineDao fineDao;

    @Autowired
    private FineDtoDoMapper mapper;

    public List<FineResponseDto> listFines() {
        return fineDao.findAllCurrent()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
