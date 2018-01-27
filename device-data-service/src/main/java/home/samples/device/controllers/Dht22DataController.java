package home.samples.device.controllers;

import home.samples.device.dto.Dht22DataCreateDto;
import home.samples.device.dto.Dht22DataDto;
import home.samples.device.services.Dht22DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="v1/dht22-data")
public class Dht22DataController {

    @Autowired
    Dht22DataService dht22DataService;

    @RequestMapping(value="/{deviceId}" , method = RequestMethod.GET)
    public List<Dht22DataDto> getDevice(@PathVariable String deviceId){
        return dht22DataService.findByDeviceId(deviceId);
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public Dht22DataDto addDevice(@RequestBody Dht22DataCreateDto createDto){
        return dht22DataService.add(createDto);
    }
}
