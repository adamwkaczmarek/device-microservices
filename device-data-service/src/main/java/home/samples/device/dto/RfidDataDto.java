package home.samples.device.dto;

import home.samples.device.model.RfidData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class RfidDataDto {


    private Long Id;
    private String uidTag;
    private Date creationDate;
    private String deviceId;

    public static RfidDataDto toDto(RfidData rfidData) {
        return new RfidDataDto(
                rfidData.getId(),
                rfidData.getUidTag(),
                rfidData.getCreationDate(),
                rfidData.getDeviceId()
        );
    }

    public static List<RfidDataDto> toDtos(List<RfidData> rfidDataList) {
        return rfidDataList.stream().map(rfiData -> toDto(rfiData)).collect(Collectors.toList());
    }

}
