package home.samples.device.repository;

import home.samples.device.dto.DeviceDto;

public interface RegisteredDeviceRepository {

    void saveDevice(DeviceDto device);
    void deleteDevice(String deviceId);
    DeviceDto findDevice(String deviceId);

}
