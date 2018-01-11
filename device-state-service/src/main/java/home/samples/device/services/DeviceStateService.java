package home.samples.device.services;


import home.samples.device.dto.DeviceStateDto;
import home.samples.device.model.DeviceState;
import home.samples.device.repository.DeviceStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeviceStateService {

    @Autowired
    DeviceStateRepository deviceStateRepository;

    public List<DeviceStateDto> findByDeviceId(String deviceId){
        List<DeviceState> deviceStates = deviceStateRepository.findByDeviceId(deviceId);
        return DeviceStateDto.toDtos(deviceStates);
    }


}
