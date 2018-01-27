package home.samples.device.dto;

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

}
