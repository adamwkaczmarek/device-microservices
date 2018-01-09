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

    public DeviceDto add(DeviceDto deviceDto){
        return DeviceDto.toDto(deviceRepository.save(new Device(deviceDto)));
    }

    public DeviceDto update(String deviceId,DeviceDto deviceDto){
        if(deviceRepository.exists(deviceId)){
            Device device = deviceRepository.findOne(deviceId);
            device.update(deviceDto);
            return DeviceDto.toDto(deviceRepository.save(device));
        }
        return null;

    }

    public void delete(String deviceId){
        if(deviceRepository.exists(deviceId)){
            Device device = deviceRepository.findOne(deviceId);
            deviceRepository.delete(device);
        }

    }

}
