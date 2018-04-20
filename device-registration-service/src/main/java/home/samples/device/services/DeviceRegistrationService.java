package home.samples.device.services;

import home.samples.device.dto.DeviceDto;
import home.samples.device.exceptions.DeviceNotFoundException;
import home.samples.device.model.Device;
import home.samples.device.repository.DeviceRepository;
import home.samples.device.sqs.RegMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DeviceRegistrationService {

    @Autowired
    DeviceRepository deviceRepository;

    public List<DeviceDto> findAll() {
        return DeviceDto.toDtos(deviceRepository.findAll());

    }

    public DeviceDto findById(String deviceId) {
        if (!deviceRepository.exists(deviceId))
            throw new DeviceNotFoundException();

        return DeviceDto.toDto(deviceRepository.findOne(deviceId));

    }


    public DeviceDto add(DeviceDto deviceDto) {
        if (!deviceRepository.exists(deviceDto.getDeviceId()))
            return DeviceDto.toDto(deviceRepository.save(new Device(deviceDto)));
        else {
            Device device = deviceRepository.findOne(deviceDto.getDeviceId());
            device.updateRegistrationDate();
            return DeviceDto.toDto(deviceRepository.save(device));
        }
    }

    public DeviceDto add(RegMsg regMsg) {
        return  add(DeviceDto.toDto(regMsg));
    }

    public void register(String deviceId) {

        if (deviceRepository.exists(deviceId)) {
            Device device = deviceRepository.findOne(deviceId);
            device.updateRegistrationDate();
            deviceRepository.save(device);
        } else
            throw new DeviceNotFoundException();

    }

    public void delete(String deviceId) {
        if (deviceRepository.exists(deviceId)) {
            Device device = deviceRepository.findOne(deviceId);
            deviceRepository.delete(device);
        } else
            throw new DeviceNotFoundException();

    }

}
