package home.samples.device.dto;

import home.samples.device.model.DeviceState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeviceStateDto {


    private String deviceId;
    private String ipAddress;
    private String listeningPort;
    private String comment;


     public static DeviceStateDto toDto(DeviceState deviceState) {

         return new DeviceStateDto();
    }

    public static List<DeviceStateDto> toDtos(List<DeviceState> deviceStateList){
         return  deviceStateList.stream().map(deviceState -> toDto(deviceState)).collect(Collectors.toList());
    }


}
