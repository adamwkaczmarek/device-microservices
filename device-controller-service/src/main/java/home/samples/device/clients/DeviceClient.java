package home.samples.device.clients;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DeviceClient {




    public String refreshDeviceState(String ip,String port){

        RestTemplate restTemplate = new RestTemplate();
        String uri=String.format("http://%1$s:%2$s/refresh-states", ip, port);
        ResponseEntity<String> restExchage=
                restTemplate.exchange(
                        uri,
                        HttpMethod.GET,
                        null, String.class);
        return restExchage.getBody();
    }

    public String getDeviceInfo(String ip,String port){
        RestTemplate restTemplate = new RestTemplate();
        String uri=String.format("http://%1$s:%2$s/info", ip, port);
        ResponseEntity<String> restExchage=
                restTemplate.exchange(
                        uri,
                        HttpMethod.GET,
                        null, String.class);
        return restExchage.getBody();
    }
}
