package home.samples.device.services;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import home.samples.device.clients.DeviceRegistrationClient;
import home.samples.device.dto.DeviceDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeviceControllerService {

    @Autowired
    DeviceRegistrationClient deviceRegistrationClient;


    @HystrixCommand
    public void sendMessageToDeviceTopic(String message,String deviceId) throws AWSIotException {

        DeviceDto device = deviceRegistrationClient.getDevice(deviceId);
        String awsAccessKeyId = System.getenv("AWS_KEY_ID");
        String awsSecretKey = System.getenv("AWS_SECRET_KEY");

        AWSIotMqttClient awsIotClient = new AWSIotMqttClient(device.getArnEndpoint(),
                                                             RandomStringUtils.randomAlphanumeric(23), awsAccessKeyId, awsSecretKey);
        awsIotClient.connect();
        try {


            awsIotClient.publish(device.getTopic(),message);
        } catch (AWSIotException e) {
            log.error("Problem with sending message to device {} Error code {} ",e.getMessage(),e.getErrorCode());
        }finally {
              awsIotClient.disconnect();
        }


    }
}
