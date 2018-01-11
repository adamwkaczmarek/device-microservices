package home.samples.device.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceStateId implements Serializable{

    @Column(name = "DEVICE_ID")
    private String deviceId;

    @Column(name = "PIN_NUMBER")
    private Integer pinNumber;


}
