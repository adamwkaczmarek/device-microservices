package home.samples.device.sqs;

import com.fasterxml.jackson.databind.ObjectMapper;
import home.samples.device.dto.DeviceDto;
import home.samples.device.services.DeviceRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class Consumer {

    @Autowired
    DeviceRegistrationService deviceRegistrationService;


    @JmsListener(destination = "${queue.registration}")
    public void processRegMessage(@Payload String message) throws IOException {
        String decodedMsg = new String(Base64.decodeBase64(message));
        log.info("Processing {}  in queue registration", decodedMsg);
        RegMsg regMsg = new ObjectMapper()
            .readerFor(RegMsg.class)
            .readValue(decodedMsg.trim());
        DeviceDto deviceDto = deviceRegistrationService.add(regMsg);
        log.info("Device {} updated !" , deviceDto.getDeviceId());

    }


    @JmsListener(destination = "${queue.deviceHealth}")
    public void processDeviceHealthMessage(@Payload String message) throws IOException {
        String decodedMsg = new String(Base64.decodeBase64(message));
        log.info("Processing {}  in queue deviceHealth", decodedMsg);
        String deviceId=decodedMsg.trim();
        deviceRegistrationService.register(deviceId);
    }
}
