package home.samples.device.dto;


import home.samples.device.model.Dht22Data;
import home.samples.device.model.RfidData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dht22DataDto {


    private Long Id;

    private Integer temp;

    private Integer humidity;

    private Date creationDate;

    private String deviceId;

    public static Dht22DataDto toDto(Dht22Data data) {
        return new Dht22DataDto(
                data.getId(),
                data.getTemp(),
                data.getHumidity(),
                data.getCreationDate(),
                data.getDeviceId()
        );
    }

    public static List<Dht22DataDto > toDtos(List<Dht22Data> dataList) {
        return dataList.stream().map(data -> toDto(data)).collect(Collectors.toList());
    }



}
