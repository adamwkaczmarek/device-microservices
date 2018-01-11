package home.samples.device.model;

import home.samples.device.dto.DeviceStateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "device_state")
@Getter
@NoArgsConstructor
public class DeviceState {

    @EmbeddedId
    DeviceStateId deviceStateId;

    Boolean activated;


    public DeviceState(DeviceStateDto deviceStateDto) {
        this.deviceStateId=new DeviceStateId(deviceStateDto.getDeviceId(),
                                                        deviceStateDto.getPinNumber());

        this.activated=deviceStateDto.getActivated();

    }

    public void update(DeviceStateDto deviceStateDto) {
        this.activated=deviceStateDto.getActivated();
    }
}
