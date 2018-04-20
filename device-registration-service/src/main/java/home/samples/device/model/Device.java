package home.samples.device.model;

import home.samples.device.dto.DeviceDto;
import home.samples.device.sqs.RegMsg;
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
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    @Id
    @Column(name = "device_id", nullable = false)
    private String deviceId;

    @Column(name = "arn_endpoint", nullable = false)
    private String arnEndpoint;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "device_desc", nullable = false)
    private String deviceDesc;

    @Column(name= "last_registration_date", nullable = false)
    private Date lastRegistrationDate;

    public Device(DeviceDto deviceDto) {
         this.deviceId=deviceDto.getDeviceId();
         this.arnEndpoint=deviceDto.getArnEndpoint();
         this.topic=deviceDto.getTopic();
         this.deviceDesc=deviceDto.getDeviceDesc();
         lastRegistrationDate=new Date();
    }


    public void updateRegistrationDate(){
        lastRegistrationDate=new Date();
    }
}
