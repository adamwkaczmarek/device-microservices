package home.samples.device.controllers;


import home.samples.device.dto.DeviceDto;
import home.samples.device.services.DeviceRegistrationService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="v1/devices")
@Log4j
public class DeviceRegistrationController {

    @Autowired
    DeviceRegistrationService deviceRegistrationService;



    @RequestMapping(method = RequestMethod.GET)
    public List<DeviceDto> getAllDevices(){
        //String correlationId = UserContextHolder.getContext().getCorrelationId();
        return deviceRegistrationService.findAll();
    }

    @RequestMapping(value="/{deviceId}" , method = RequestMethod.GET)
    public DeviceDto getDevice(@PathVariable String deviceId){
        return deviceRegistrationService.findById(deviceId);
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public DeviceDto addDevice(@RequestBody DeviceDto deviceDto){
        return deviceRegistrationService.addOrUpdate(deviceDto);
    }

    @RequestMapping(value="/register-activity/{deviceId}", method = RequestMethod.PUT)
    public void registerDevice(@PathVariable String deviceId){
        deviceRegistrationService.register(deviceId);
    }


    @RequestMapping(value="/{deviceId}" , method = RequestMethod.DELETE)
    public void deleteDevice(@PathVariable String deviceId){
        deviceRegistrationService.delete(deviceId);
    }
}
