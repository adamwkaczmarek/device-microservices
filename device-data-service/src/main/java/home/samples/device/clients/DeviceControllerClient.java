package home.samples.device.clients;

import home.samples.device.dto.SimpleMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DeviceControllerClient {

    @Autowired
    RestTemplate restTemplate;


    public void sendSimpleMessage(String deviceId,String message){
        HttpEntity<SimpleMessageDto> request = new HttpEntity<>(new SimpleMessageDto(message));
        ResponseEntity<Void> restExchage=
                restTemplate.exchange(
                        "http://device-controller-service/v1/device-controller/send-simple-msg/{deviceId}",
                        HttpMethod.POST,
                        request,Void.class, deviceId );
    }
}
