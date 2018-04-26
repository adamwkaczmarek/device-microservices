package home.samples.device.kafka.producers;

import home.samples.device.kafka.model.DeviceDataUpdateMsg;
import home.samples.device.kafka.streams.DeviceDataUpdateStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
@Slf4j
public class DeviceDataUpdateProducer {

    private final DeviceDataUpdateStreams streams;

    public DeviceDataUpdateProducer(DeviceDataUpdateStreams streams) {
        this.streams = streams;
    }

    public void sendDeviceDataUpdateMsg(DeviceDataUpdateMsg updateMsg){
        log.debug("Sending device update message {}" ,updateMsg);

        MessageChannel messageChannel = streams.outboundDeviceDataUpdate();
        messageChannel.send(MessageBuilder.withPayload(updateMsg).setHeader(MessageHeaders.CONTENT_TYPE,
                                                                            MimeTypeUtils.APPLICATION_JSON).build());
    }
}
