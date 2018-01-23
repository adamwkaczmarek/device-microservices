package home.samples.device.controllers;

import home.samples.device.dto.RfidDataCreateDto;
import home.samples.device.dto.RfidDataDto;
import home.samples.device.services.RfidDataService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="v1/rfid-data")
@Log4j
public class RfidDataController {

    @Autowired
    RfidDataService rfidDataService;

    @RequestMapping(value="/{deviceId}" , method = RequestMethod.GET)
    public List<RfidDataDto> getDevice(@PathVariable String deviceId){
        return rfidDataService.findByDeviceId(deviceId);
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public RfidDataDto addDevice(@RequestBody RfidDataCreateDto createDto){
        return rfidDataService.add(createDto);
    }

}


