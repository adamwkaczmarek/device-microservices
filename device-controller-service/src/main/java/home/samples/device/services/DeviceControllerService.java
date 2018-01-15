package home.samples.device.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import home.samples.device.clients.DeviceClient;
import home.samples.device.clients.DeviceRegistrationClient;
import home.samples.device.dto.DeviceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceControllerService {

    @Autowired
    DeviceRegistrationClient deviceRegistrationClient;

    @Autowired
    DeviceClient deviceClient;

    @HystrixCommand
    public void refreshDeviceState(String deviceId) {
        DeviceDto device = deviceRegistrationClient.getDevice(deviceId);

        deviceClient.refreshDeviceState(device.getIpAddress(), device.getListeningPort());

    }

    @HystrixCommand
    public String getDeviceInfo(String deviceId) {
        DeviceDto device = deviceRegistrationClient.getDevice(deviceId);
        return deviceClient.getDeviceInfo(device.getIpAddress(), device.getListeningPort());
    }
}
