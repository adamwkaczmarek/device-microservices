package home.samples.device.services;

import home.samples.device.dto.DeviceDto;
import home.samples.device.exceptions.DeviceNotFoundException;
import home.samples.device.model.Device;
import home.samples.device.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DeviceRegistrationService {

    @Autowired
    DeviceRepository deviceRepository;

    public List<DeviceDto> findAll(){
        return DeviceDto.toDtos(deviceRepository.findAll());

    }

    public DeviceDto findById(String deviceId){
        return DeviceDto.toDto(deviceRepository.findOne(deviceId));

    }


    public DeviceDto add(DeviceDto deviceDto){
           return DeviceDto.toDto(deviceRepository.save(new Device(deviceDto)));
    }

    public void register(String deviceId){

        if(deviceRepository.exists(deviceId)){
            Device device = deviceRepository.findOne(deviceId);
            device.updateRegistrationDate();
            deviceRepository.save(device);
       }else
           throw new DeviceNotFoundException();

    }

    public void delete(String deviceId){
        if(deviceRepository.exists(deviceId)){
            Device device = deviceRepository.findOne(deviceId);
            deviceRepository.delete(device);
        }else
            throw new DeviceNotFoundException();

    }

}
