package home.samples.device.sqs;


import com.fasterxml.jackson.databind.ObjectMapper;
import home.samples.device.dto.Dht22DataCreateDto;

import home.samples.device.services.Dht22DataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class Consumer {

    @Autowired
    Dht22DataService dht22DataService;


    @JmsListener(destination = "${queue.data}")
    public void processDhtDataMessage(@Payload String message) throws IOException {
        String decodedMsg = new String(Base64.decodeBase64(message));
        log.info("Processing {}  in queue registration", decodedMsg);
        Dht22Msg dht22DataMsg = new ObjectMapper()
            .readerFor(Dht22Msg.class)
            .readValue(decodedMsg.trim());
        dht22DataService.add(Dht22DataCreateDto.toDto(dht22DataMsg));

        log.info("Data from {} saved !");

    }

}
