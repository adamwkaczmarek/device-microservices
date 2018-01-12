package home.samples.device.controllers;

import home.samples.device.dto.DeviceStateDto;
import home.samples.device.services.DeviceStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1/device-state")
public class DeviceStateController {

    @Autowired
    DeviceStateService deviceService;

    @RequestMapping(value = "/{deviceId}", method = RequestMethod.GET)
    public List<DeviceStateDto> getDeviceStates(@PathVariable String deviceId) {
        return deviceService.findByDeviceId(deviceId);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public DeviceStateDto addDeviceState(@RequestBody  DeviceStateDto deviceStateDto) {
        return deviceService.addDeviceState(deviceStateDto);
    }

    @RequestMapping(value = "/{deviceId}/{pinNumber}", method = RequestMethod.DELETE)
    public void deleteDeviceState(@PathVariable String deviceId, @PathVariable Integer pinNumber) {
        deviceService.deleteDeviceState(deviceId, pinNumber);
    }

     @RequestMapping(value = "/{deviceId}", method = RequestMethod.DELETE)
    public void deleteAllDeviceStates(@PathVariable String deviceId) {
        deviceService.deleteAllDeviceStates(deviceId);
    }

    @RequestMapping(value = "/activate/{deviceId}/{pinNumber}", method = RequestMethod.PUT)
    public void activateDevicePin(@PathVariable String deviceId, @PathVariable Integer pinNumber) {
        deviceService.changePinState(deviceId, pinNumber, true);
    }

    @RequestMapping(value = "/deactivate/{deviceId}/{pinNumber}", method = RequestMethod.PUT)
    public void deactivateDevicePin(@PathVariable String deviceId,
        @PathVariable Integer pinNumber) {
        deviceService.changePinState(deviceId, pinNumber, false);
    }


}
