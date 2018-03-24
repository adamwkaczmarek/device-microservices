package home.samples.device.dto;

import home.samples.device.model.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeviceDto {


    private String deviceId;
    private String arnEndpoint;
    private String topic;
    private String deviceDesc;


     public static DeviceDto toDto(Device device) {
        return new DeviceDto(
            device.getDeviceId(),
            device.getArnEndpoint(),
            device.getTopic(),
            device.getDeviceDesc()
         );
    }

    public static List<DeviceDto> toDtos(List<Device> deviceList){
         return  deviceList.stream().map(device -> toDto(device)).collect(Collectors.toList());
    }


}
