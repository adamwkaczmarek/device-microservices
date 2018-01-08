package home.samples.device.controllers;

import home.samples.device.dto.DeviceDto;
import home.samples.device.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="v1/devices")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @RequestMapping(value="/",method = RequestMethod.GET)
    public List<DeviceDto> getAllDevices(){
        return deviceService.findAll();
    }
}
