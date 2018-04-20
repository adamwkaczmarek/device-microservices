package home.samples.device.dto;

import home.samples.device.sqs.Dht22Msg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dht22DataCreateDto {

    private Integer temp;

    private Integer humidity;

    private String deviceId;

     public  static  Dht22DataCreateDto toDto(Dht22Msg message){
        return new Dht22DataCreateDto(message.getDeviceData().getTemp(),
                                message.getDeviceData().getHum(),
                                message.getDeviceId());
    }

}
