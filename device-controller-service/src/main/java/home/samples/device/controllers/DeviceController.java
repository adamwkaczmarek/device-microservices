package home.samples.device.controllers;

import home.samples.device.services.DeviceControllerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="v1/device-controller")
public class DeviceController {

    @Autowired
    DeviceControllerService deviceControllerService;

    @RequestMapping(value="/refresh-state/{deviceId}", method = RequestMethod.PUT)
    public void refreshDeviceState(@PathVariable String deviceId){
        deviceControllerService.refreshDeviceState(deviceId);
    }
    @RequestMapping(value="/device-info/{deviceId}", method = RequestMethod.GET)
    public String getDeviceInfo(@PathVariable String deviceId){
       return deviceControllerService.getDeviceInfo(deviceId);
    }
}
