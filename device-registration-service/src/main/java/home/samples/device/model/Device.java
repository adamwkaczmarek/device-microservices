package home.samples.device.model;

import home.samples.device.dto.DeviceDto;
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
    private String id;

    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @Column(name = "listening_port", nullable = false)
    private String listeningPort;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name= "last_registration_date", nullable = false)
    private Date lastRegistrationDate;

    public Device(DeviceDto deviceDto) {
         this.id=deviceDto.getDeviceId();
         this.ipAddress=deviceDto.getIpAddress();
         this.listeningPort=deviceDto.getListeningPort();
         this.comment=deviceDto.getComment();
         lastRegistrationDate=new Date();
    }

    public void update(DeviceDto deviceDto){
        this.ipAddress=deviceDto.getIpAddress();
        this.listeningPort=deviceDto.getListeningPort();
        this.comment=deviceDto.getComment();
        lastRegistrationDate=new Date();
    }
}
