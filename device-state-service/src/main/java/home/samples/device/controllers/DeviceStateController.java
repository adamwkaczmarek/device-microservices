package home.samples.device.controllers;

import home.samples.device.dto.DeviceStateDto;
import home.samples.device.services.DeviceStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="v1/device-state")
public class DeviceStateController {

    @Autowired
    DeviceStateService deviceService;

    @RequestMapping(value = "/{deviceId}",method = RequestMethod.GET)
    public List<DeviceStateDto> getDeviceStates(@PathVariable String deviceId){
        return  deviceService.findByDeviceId(deviceId);
    }

}
