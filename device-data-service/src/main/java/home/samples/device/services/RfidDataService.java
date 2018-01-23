package home.samples.device.services;

import home.samples.device.dto.RfidDataCreateDto;
import home.samples.device.dto.RfidDataDto;
import home.samples.device.model.RfidData;
import home.samples.device.repository.RfidDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RfidDataService {

    @Autowired
    RfidDataRepository rfidDataRepository;

    public List<RfidDataDto> findByDeviceId(String deviceId){
                return RfidDataDto.toDtos(rfidDataRepository.findByDeviceId(deviceId));
    }

    public RfidDataDto add(RfidDataCreateDto rfidDataDto){
        return RfidDataDto.toDto(rfidDataRepository.save(new RfidData(rfidDataDto)));
    }
}
