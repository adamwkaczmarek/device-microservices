package home.samples.device.controllers;

import home.samples.device.dto.DeviceDto;
import home.samples.device.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(method = RequestMethod.POST)
    public DeviceDto addDevice(@RequestBody DeviceDto deviceDto){
        return deviceService.add(deviceDto);
    }

    @RequestMapping(value="/{deviceId}", method = RequestMethod.PUT)
    public DeviceDto updateDevice(@PathVariable String deviceId,@RequestBody DeviceDto deviceDto){
        return deviceService.update(deviceId,deviceDto);
    }

    @RequestMapping(value="/{deviceId}" , method = RequestMethod.DELETE)
    public void deleteDevice(@PathVariable String deviceId){
        deviceService.delete(deviceId);
    }
}
