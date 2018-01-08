package home.samples.device.model;

import home.samples.device.dto.DeviceDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "device")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "ip_adderss", nullable = false)
    private String ipAddress;

    @Column(name = "listening_port", nullable = false)
    private String listeningPort;

    @Column(name = "comment", nullable = false)
    private String comment;

    public Device(DeviceDto deviceDto) {
         this.id=deviceDto.getId();
         this.ipAddress=deviceDto.getIpAddress();
         this.listeningPort=deviceDto.getListeningPort();
         this.comment=deviceDto.getComment();
    }
}
