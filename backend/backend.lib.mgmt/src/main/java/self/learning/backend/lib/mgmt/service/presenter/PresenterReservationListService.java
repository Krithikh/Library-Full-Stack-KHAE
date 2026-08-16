package self.learning.backend.lib.mgmt.service.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import self.learning.backend.lib.mgmt.dao.ReservationDao;
import self.learning.backend.lib.mgmt.dto.response.ReservationResponseDto;
import self.learning.backend.lib.mgmt.mapper.ReservationDtoDoMapper;

@Service
public class PresenterReservationListService {

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private ReservationDtoDoMapper mapper;

    public List<ReservationResponseDto> listReservations() {
        return reservationDao.findAllCurrent()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
