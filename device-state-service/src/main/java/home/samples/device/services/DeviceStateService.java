package home.samples.device.services;


import home.samples.device.dto.DeviceStateDto;
import home.samples.device.model.DeviceState;
import home.samples.device.model.DeviceStateId;
import home.samples.device.repository.DeviceStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeviceStateService {

    @Autowired
    DeviceStateRepository deviceStateRepository;

    public List<DeviceStateDto> findByDeviceId(String deviceId) {
        List<DeviceState> deviceStates = deviceStateRepository.findByDeviceId(deviceId);
        return DeviceStateDto.toDtos(deviceStates);
    }


    public DeviceStateDto addDeviceState(DeviceStateDto deviceStateDto) {
        return DeviceStateDto.toDto(deviceStateRepository.save(new DeviceState(deviceStateDto)));
    }

    public void changePinState(String deviceId, Integer pinNumber, Boolean activate) {

        DeviceStateId id = new DeviceStateId(deviceId, pinNumber);
        if (deviceStateRepository.exists(id)) {
            DeviceState deviceState = deviceStateRepository.findOne(id);
            deviceState.update(activate);
            deviceStateRepository.save(deviceState);
        }
    }

    public void deleteDeviceState(String deviceId, Integer pinNumber) {
        DeviceStateId id = new DeviceStateId(deviceId, pinNumber);
        if (deviceStateRepository.exists(id)) {
            DeviceState deviceState = deviceStateRepository.findOne(id);
            deviceStateRepository.delete(deviceState);
        }
    }

    public void deleteAllDeviceStates(String deviceId) {
        List<DeviceState> devices = deviceStateRepository.findByDeviceId(deviceId);
        if (!devices.isEmpty())
            deviceStateRepository.delete(devices);
    }

}
