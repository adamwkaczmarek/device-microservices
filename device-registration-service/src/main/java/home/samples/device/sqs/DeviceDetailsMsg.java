package home.samples.device.sqs;

import lombok.Data;

@Data
public class DeviceDetailsMsg {
    private String arnEndpoint;
    private String topic;
    private String deviceDesc;

}
