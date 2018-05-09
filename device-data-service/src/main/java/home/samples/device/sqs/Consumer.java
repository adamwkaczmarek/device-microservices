package home.samples.device.sqs;


import com.fasterxml.jackson.databind.ObjectMapper;
import home.samples.device.dto.Dht22DataCreateDto;

import home.samples.device.dto.Dht22DataDto;
import home.samples.device.kafka.model.DeviceDataUpdateMsg;
import home.samples.device.kafka.producers.DeviceDataUpdateProducer;
import home.samples.device.services.Dht22DataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class Consumer {


    private final Dht22DataService dht22DataService;
    private final DeviceDataUpdateProducer deviceDataUpdateProducer;

    public Consumer(Dht22DataService dht22DataService, DeviceDataUpdateProducer deviceDataUpdateProducer) {
        this.dht22DataService = dht22DataService;
        this.deviceDataUpdateProducer = deviceDataUpdateProducer;
    }

    @JmsListener(destination = "${queue.data}")
    public void processDhtDataMessage(@Payload String message, @Header(JmsHeaders.MESSAGE_ID) String messageId) throws IOException {
        String decodedMsg = new String(Base64.decodeBase64(message));
        log.info("Processing registration queue message with id  {}  and payload {}  ", messageId,decodedMsg);
        Dht22Msg dht22DataMsg = new ObjectMapper()
            .readerFor(Dht22Msg.class)
            .readValue(decodedMsg.trim());
        Dht22DataDto dht22DataDto = dht22DataService.add(Dht22DataCreateDto.toDto(dht22DataMsg));
        deviceDataUpdateProducer.sendDeviceDataUpdateMsg(new DeviceDataUpdateMsg(dht22DataDto.getDeviceId(),"DHT_DATA",messageId));

        log.info("Data from {} saved !");

    }

}
