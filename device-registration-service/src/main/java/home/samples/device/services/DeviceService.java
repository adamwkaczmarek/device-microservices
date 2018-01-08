package home.samples.device.services;

import home.samples.device.dto.DeviceDto;
import home.samples.device.model.Device;
import home.samples.device.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    public List<DeviceDto> findAll(){
        return DeviceDto.toDtos(deviceRepository.findAll());

    }

}
