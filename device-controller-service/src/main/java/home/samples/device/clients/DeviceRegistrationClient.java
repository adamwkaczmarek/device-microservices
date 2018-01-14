package home.samples.device.clients;


import home.samples.device.dto.DeviceDto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DeviceRegistrationClient {

    @Autowired
    RestTemplate restTemplate;

    public DeviceDto getDevice(String deviceId){
        ResponseEntity <DeviceDto>  restExchage=
                restTemplate.exchange(
                        "http://device-registration-service/v1/devices/{deviceId}",
                        HttpMethod.GET,
                        null, DeviceDto.class, deviceId );
        return restExchage.getBody();
    }



}
