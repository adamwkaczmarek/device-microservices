package home.samples.device.controllers;

import home.samples.device.services.DeviceStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="v1/device-state")
public class DeviceStateController {

    @Autowired
    DeviceStateService deviceService;

}
