package home.samples.device.clients;

import home.samples.device.dto.SimpleMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class DeviceControllerClient {

    @Autowired
    @Qualifier("empty")
    RestTemplate restTemplate;


    public void sendSimpleMessage(String deviceId, String token, String message) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.add("Authorization", "Bearer " + token);
        HttpEntity<SimpleMessageDto> request = new HttpEntity<>(new SimpleMessageDto(message),httpHeaders);


        restTemplate.exchange(
            "http://zuul-service/api/device-ctrl/v1/device-controller/send-simple-msg/{deviceId}",
            HttpMethod.POST,
            request, Void.class, deviceId);
    }
}
