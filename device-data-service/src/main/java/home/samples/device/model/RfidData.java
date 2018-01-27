package home.samples.device.model;

import home.samples.device.dto.RfidDataCreateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="RFID_DATA")
@Getter
@NoArgsConstructor
public class RfidData {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long Id;

    @Column(name = "UID_TAG", nullable = false)
    private String uidTag;

    @Column(name = "CREATION_DATE", nullable = false)
    private Date creationDate;

    @Column(name="DEVICE_ID", nullable = false)
    private String deviceId;

    public RfidData(RfidDataCreateDto createDto) {
        this.uidTag = createDto.getUidTag();
        this.deviceId = createDto.getDeviceId();
        this.creationDate= new Date();
    }
}
