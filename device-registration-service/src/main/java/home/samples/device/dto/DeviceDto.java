package home.samples.device.dto;

import home.samples.device.model.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeviceDto {


    private String id;
    private String ipAddress;
    private String listeningPort;
    private String comment;


     public static DeviceDto toDto(Device device) {
        return new DeviceDto(
            device.getId(),
            device.getIpAddress(),
            device.getListeningPort(),
            device.getComment()
         );
    }

    public static List<DeviceDto> toDtos(List<Device> deviceList){
         return  deviceList.stream().map(device -> toDto(device)).collect(Collectors.toList());
    }


}
