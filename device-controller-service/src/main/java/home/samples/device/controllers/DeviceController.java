package home.samples.device.controllers;

import com.amazonaws.services.iot.client.AWSIotException;
import home.samples.device.dto.SimpleMessageDto;
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


    @RequestMapping(value = "/send-simple-msg/{deviceId}", method = RequestMethod.POST)
    public void sendSimpleMessage(@PathVariable String deviceId, @RequestBody SimpleMessageDto messageDto) throws AWSIotException, IOException {
        deviceControllerService.sendMessageToDeviceTopic(messageDto, deviceId);
    }
}
