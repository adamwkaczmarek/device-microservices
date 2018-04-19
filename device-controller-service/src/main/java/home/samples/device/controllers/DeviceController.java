package home.samples.device.controllers;

import com.amazonaws.services.iot.client.AWSIotException;
import home.samples.device.dto.DeviceMessageDto;
import home.samples.device.services.DeviceControllerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "v1/device-controller")
public class DeviceController {

    @Autowired
    DeviceControllerService deviceControllerService;


    @RequestMapping(value = "/send-msg/{deviceId}", method = RequestMethod.POST)
    public void getDeviceInfo(@PathVariable String deviceId, @RequestBody DeviceMessageDto messageDto) throws AWSIotException, IOException {
        deviceControllerService.sendMessageToDeviceTopic(messageDto, deviceId);
    }
}
