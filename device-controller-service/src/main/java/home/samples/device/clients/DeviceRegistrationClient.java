package home.samples.device.clients;


import home.samples.context.UserContextHolder;
import home.samples.device.clients.oAuth.AuthorizationCodeTokenService;
import home.samples.device.clients.oAuth.OAuth2Token;
import home.samples.device.dto.DeviceDto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class DeviceRegistrationClient {

    public static final String DEVICE_REG_SERVICE_URL = "http://zuul-service/api/device-reg/v1/devices/{deviceId}";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("empty")
    private RestTemplate restTemplateEmpty;

    @Autowired
    private AuthorizationCodeTokenService authorizationCodeTokenService;

    @Value("${security.tech-user-name}")
    private String techUser;

    @Value("${security.tech-password}")
    private String techPassword;


    public DeviceDto getDevice(String deviceId) {

        ResponseEntity<DeviceDto> restExchage = null;

        //TO DO need to be refactor
        if (UserContextHolder.getContext().getAuthToken().isEmpty()) {
            OAuth2Token token = authorizationCodeTokenService.getToken(techUser, techPassword);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            httpHeaders.add("Authorization", "Bearer " + token.getAccessToken());
            httpHeaders.add("correlation-id", "");
            httpHeaders.add("user-id", techUser);
            HttpEntity request = new HttpEntity<>(httpHeaders);

            restExchage =
                restTemplateEmpty.exchange(
                    DEVICE_REG_SERVICE_URL,
                    HttpMethod.GET,
                    request, DeviceDto.class, deviceId);

        } else {
            restExchage =
                restTemplate.exchange(
                    DEVICE_REG_SERVICE_URL,
                    HttpMethod.GET,
                    null, DeviceDto.class, deviceId);
        }


        return restExchage.getBody();
    }


}
