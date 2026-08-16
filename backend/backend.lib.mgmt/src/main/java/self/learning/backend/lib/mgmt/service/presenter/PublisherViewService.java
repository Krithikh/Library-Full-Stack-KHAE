package self.learning.backend.lib.mgmt.service.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import self.learning.backend.lib.mgmt.dao.PublisherDao;
import self.learning.backend.lib.mgmt.dto.response.PublisherResponseDto;
import self.learning.backend.lib.mgmt.mapper.PublisherDtoDoMapper;

@Service
public class PublisherViewService {

    @Autowired private PublisherDao publisherDao;
    @Autowired private PublisherDtoDoMapper mapper;

    public List<PublisherResponseDto> findAll() {
        return publisherDao.findAll().stream().map(mapper::toResponse).toList();
    }

    public PublisherResponseDto findById(Long id) {
        return publisherDao.findById(id).map(mapper::toResponse).orElse(null);
    }
}
