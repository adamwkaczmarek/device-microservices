package home.samples.device.kafka.consumers;

import com.amazonaws.services.iot.client.AWSIotException;
import home.samples.context.UserContext;
import home.samples.context.UserContextHolder;
import home.samples.device.clients.oAuth.AuthorizationCodeTokenService;
import home.samples.device.clients.oAuth.OAuth2Token;
import home.samples.device.controllers.DeviceController;
import home.samples.device.dto.SimpleMessageDto;
import home.samples.device.kafka.model.DeviceDataUpdateMsg;
import home.samples.device.kafka.streams.DeviceDataUpdateStreams;
import home.samples.device.services.DeviceControllerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class DeviceDataChangeListener {


    private final DeviceControllerService deviceControllerService;


    public DeviceDataChangeListener(DeviceControllerService deviceControllerService) {
        this.deviceControllerService = deviceControllerService;
    }

    @StreamListener(DeviceDataUpdateStreams.INPUT)
    public void handleDeviceDataChange(
        @Payload DeviceDataUpdateMsg deviceDataUpdateMsg) throws IOException, AWSIotException {

        log.info("Received deviceDataUpdateMsg: {}", deviceDataUpdateMsg);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleMessageDto simpleMessageDto = new SimpleMessageDto(
            "DHT data updated    " + df.format(new Date()));
        deviceControllerService
            .sendMessageToDeviceTopic(simpleMessageDto, deviceDataUpdateMsg.getDeviceId());

    }
}
