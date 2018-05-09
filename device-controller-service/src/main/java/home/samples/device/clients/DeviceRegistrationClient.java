package home.samples.device.clients;


import home.samples.context.UserContext;
import home.samples.context.UserContextHolder;
import home.samples.device.clients.oAuth.AuthorizationCodeTokenService;
import home.samples.device.clients.oAuth.OAuth2Token;
import home.samples.device.dto.DeviceDto;
import home.samples.device.repository.RegisteredDeviceRepository;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DeviceRegistrationClient {

    public static final String DEVICE_REG_SERVICE_URL = "http://zuul-service/api/device-reg/v1/devices/{deviceId}";

    @Value("${security.tech-user-name}")
    private String techUser;

    @Value("${security.tech-password}")
    private String techPassword;

    private final RestTemplate restTemplate;

    private final AuthorizationCodeTokenService authorizationCodeTokenService;

    private final RegisteredDeviceRepository registeredDeviceRepository;

    public DeviceRegistrationClient(RestTemplate restTemplate,
        AuthorizationCodeTokenService authorizationCodeTokenService,RegisteredDeviceRepository registeredDeviceRepository) {
        this.restTemplate = restTemplate;
        this.authorizationCodeTokenService = authorizationCodeTokenService;
        this.registeredDeviceRepository=registeredDeviceRepository;
    }


    public DeviceDto getDevice(String deviceId) {

        // redis cache check
        DeviceDto deviceDto = checkRedisCache(deviceId);
        if(deviceDto!=null){
            log.info(" Device {} found in redis cache ",deviceDto.getDeviceId());
            return deviceDto;
        }

        ResponseEntity<DeviceDto> restExchage;
        HttpEntity request = null;

        if (UserContextHolder.getContext().getAuthToken().isEmpty()) {
            OAuth2Token token = authorizationCodeTokenService.getToken(techUser, techPassword);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            httpHeaders.add(UserContext.AUTH_TOKEN, "Bearer " + token.getAccessToken());
            request = new HttpEntity<>(httpHeaders);
        }

        restExchage =
            restTemplate.exchange(
                DEVICE_REG_SERVICE_URL,
                HttpMethod.GET,
                request, DeviceDto.class, deviceId);


        DeviceDto body = restExchage.getBody();
        registeredDeviceRepository.saveDevice(body);
        return body;
    }

    private DeviceDto checkRedisCache(String deviceId){

        DeviceDto device = registeredDeviceRepository.findDevice(deviceId);

        return  device;
    }


}
