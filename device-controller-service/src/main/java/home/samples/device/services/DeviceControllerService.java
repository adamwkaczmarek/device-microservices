package home.samples.device.services;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import home.samples.device.clients.DeviceRegistrationClient;
import home.samples.device.dto.DeviceDto;
import home.samples.device.dto.SimpleMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class DeviceControllerService {

    @Autowired
    DeviceRegistrationClient deviceRegistrationClient;


    @HystrixCommand(
            commandProperties =
                    {@HystrixProperty(
                            name="execution.isolation.thread.timeoutInMilliseconds",
                            value = "12000")})
    public void sendMessageToDeviceTopic(SimpleMessageDto messageDto, String deviceId) throws AWSIotException, IOException {

        DeviceDto device = deviceRegistrationClient.getDevice(deviceId);
        String awsAccessKeyId = System.getenv("AWS_KEY_ID");
        String awsSecretKey = System.getenv("AWS_SECRET_KEY");

        AWSIotMqttClient awsIotClient = new AWSIotMqttClient(device.getArnEndpoint(),
                                                             RandomStringUtils.randomAlphanumeric(23), awsAccessKeyId, awsSecretKey);
        awsIotClient.connect();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String message = objectMapper.writeValueAsString(messageDto);

            awsIotClient.publish(device.getTopic(),message);
        } catch (AWSIotException e) {
            log.error("Problem with sending message to device {} Error code {} ",e.getMessage(),e.getErrorCode());
        }finally {
              awsIotClient.disconnect();
        }


    }
}
