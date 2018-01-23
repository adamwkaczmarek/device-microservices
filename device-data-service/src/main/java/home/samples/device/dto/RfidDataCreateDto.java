package home.samples.device.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RfidDataCreateDto {

    private String uidTag;
    private String deviceId;


}
