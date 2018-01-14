package home.samples.device.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeviceDto {


    private String deviceId;
    private String ipAddress;
    private String listeningPort;
    private String comment;


}
