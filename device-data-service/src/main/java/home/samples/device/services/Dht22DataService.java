package home.samples.device.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import home.samples.device.clients.DeviceControllerClient;
import home.samples.device.dto.Dht22DataCreateDto;
import home.samples.device.dto.Dht22DataDto;
import home.samples.device.model.Dht22Data;
import home.samples.device.repository.Dht22DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class Dht22DataService {

    @Autowired
    Dht22DataRepository dht22DataRepository;

    @Autowired
    DeviceControllerClient deviceControllerClient;

    @HystrixCommand(
            commandProperties =
                    {@HystrixProperty(
                            name="execution.isolation.thread.timeoutInMilliseconds",
                            value = "12000")})
    public Dht22DataDto add(Dht22DataCreateDto dht22DataCreateDto){
        Dht22DataDto dht22DataDto = Dht22DataDto.toDto(dht22DataRepository.save(new Dht22Data(dht22DataCreateDto)));
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        deviceControllerClient.sendSimpleMessage(dht22DataDto.getDeviceId(),"DHT data updated"+df.format(dht22DataDto.getCreationDate()));
        return dht22DataDto;
    }

    public List<Dht22DataDto> findByDeviceId(String deviceId){
        return  Dht22DataDto.toDtos(dht22DataRepository.findByDeviceId(deviceId));
    }

}
