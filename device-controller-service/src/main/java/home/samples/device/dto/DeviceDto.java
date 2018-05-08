package home.samples.device.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeviceDto implements Serializable{

    private String deviceId;
    private String arnEndpoint;
    private String topic;
    private String deviceDesc;


}
