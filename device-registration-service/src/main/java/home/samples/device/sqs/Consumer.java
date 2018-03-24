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
    public void processMessageB(@Payload String message) throws IOException {
        String decodedMsg = new String(Base64.decodeBase64(message));
        log.info("Processing {}  in queue registration", decodedMsg);
        RegMessage regMessage = new ObjectMapper()
            .readerFor(RegMessage.class)
            .readValue(decodedMsg.trim());
        DeviceDto deviceDto = deviceRegistrationService.addOrUpdate(regMessage);
        log.info("Device {} updated !" , deviceDto.getDeviceId());

    }
}
