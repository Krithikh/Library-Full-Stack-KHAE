package self.learning.backend.lib.mgmt.service.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import self.learning.backend.lib.mgmt.dao.ReservationDao;
import self.learning.backend.lib.mgmt.dto.response.ReservationResponseDto;
import self.learning.backend.lib.mgmt.mapper.ReservationDtoDoMapper;

@Service
public class ReservationViewService {

    @Autowired private ReservationDao reservationDao;
    @Autowired private ReservationDtoDoMapper mapper;

    public List<ReservationResponseDto> findAll() {
        return reservationDao.findAll().stream().map(mapper::toResponse).toList();
    }

    public ReservationResponseDto findById(Long id) {
        return reservationDao.findById(id).map(mapper::toResponse).orElse(null);
    }
}
