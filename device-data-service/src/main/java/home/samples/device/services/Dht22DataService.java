package home.samples.device.services;

import home.samples.device.dto.Dht22DataCreateDto;
import home.samples.device.dto.Dht22DataDto;
import home.samples.device.model.Dht22Data;
import home.samples.device.repository.Dht22DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Dht22DataService {

    @Autowired
    Dht22DataRepository dht22DataRepository;

    public Dht22DataDto add(Dht22DataCreateDto dht22DataCreateDto){
     return  Dht22DataDto.toDto(dht22DataRepository.save(new Dht22Data(dht22DataCreateDto)));
    }

    public List<Dht22DataDto> findByDeviceId(String deviceId){
        return  Dht22DataDto.toDtos(dht22DataRepository.findByDeviceId(deviceId));
    }

}
