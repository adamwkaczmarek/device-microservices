package home.samples.device.model;

import home.samples.device.dto.DeviceStateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "device")
@Getter
@NoArgsConstructor
public class DeviceState {



    public DeviceState(DeviceStateDto deviceStateDto) {

    }

    public void update(DeviceStateDto deviceStateDto){

    }
}
