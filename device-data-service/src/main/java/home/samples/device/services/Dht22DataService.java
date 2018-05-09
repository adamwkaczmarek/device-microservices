package home.samples.device.services;


import home.samples.device.dto.Dht22DataCreateDto;
import home.samples.device.dto.Dht22DataDto;
import home.samples.device.kafka.model.DeviceDataUpdateMsg;
import home.samples.device.kafka.producers.DeviceDataUpdateProducer;
import home.samples.device.model.Dht22Data;
import home.samples.device.repository.Dht22DataRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Dht22DataService {


    private final Dht22DataRepository dht22DataRepository;

    public Dht22DataService(Dht22DataRepository dht22DataRepository) {
        this.dht22DataRepository = dht22DataRepository;
    }

    public Dht22DataDto add(Dht22DataCreateDto dht22DataCreateDto){
        Dht22DataDto dht22DataDto = Dht22DataDto.toDto(dht22DataRepository.save(new Dht22Data(dht22DataCreateDto)));
        return dht22DataDto;
    }

    public List<Dht22DataDto> findByDeviceId(String deviceId){
        return  Dht22DataDto.toDtos(dht22DataRepository.findByDeviceId(deviceId));
    }

}
