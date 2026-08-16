package self.learning.backend.lib.mgmt.mapper;

import org.springframework.stereotype.Component;
import self.learning.backend.lib.mgmt.dataobject.ReservationDO;
import self.learning.backend.lib.mgmt.dto.request.ReservationCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.ReservationUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.ReservationResponseDto;

@Component
public class ReservationDtoDoMapper {

    public ReservationDO toDO(ReservationCreateRequestDto request) {
        ReservationDO dataObject = new ReservationDO();
        dataObject.setReservationNumber(request.getReservationNumber());
        dataObject.setMembershipId(request.getMembershipId());
        dataObject.setBookId(request.getBookId());
        dataObject.setReservedDate(request.getReservedDate());
        dataObject.setStatus(request.getStatus());
        return dataObject;
    }

    public void applyUpdate(ReservationUpdateRequestDto request, ReservationDO dataObject) {
        dataObject.setReservationNumber(request.getReservationNumber());
        dataObject.setMembershipId(request.getMembershipId());
        dataObject.setBookId(request.getBookId());
        dataObject.setReservedDate(request.getReservedDate());
        dataObject.setStatus(request.getStatus());
    }

    public ReservationResponseDto toResponse(ReservationDO dataObject) {
        ReservationResponseDto response = new ReservationResponseDto();
        response.setReservationId(dataObject.getReservationId());
        response.setReservationNumber(dataObject.getReservationNumber());
        response.setMembershipId(dataObject.getMembershipId());
        response.setBookId(dataObject.getBookId());
        response.setReservedDate(dataObject.getReservedDate());
        response.setStatus(dataObject.getStatus());
        return response;
    }
}
