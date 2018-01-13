package home.samples.device.controllers;

import home.samples.device.dto.DeviceDto;
import home.samples.device.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="v1/devices")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @RequestMapping(method = RequestMethod.GET)
    public List<DeviceDto> getAllDevices(){
        return deviceService.findAll();
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public DeviceDto addDevice(@RequestBody DeviceDto deviceDto){
        return deviceService.add(deviceDto);
    }

    @RequestMapping(value="/register-activity/{deviceId}", method = RequestMethod.PUT)
    public void registerDevice(@PathVariable String deviceId){
        deviceService.register(deviceId);
    }


    @RequestMapping(value="/{deviceId}" , method = RequestMethod.DELETE)
    public void deleteDevice(@PathVariable String deviceId){
        deviceService.delete(deviceId);
    }
}
