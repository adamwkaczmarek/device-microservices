package home.samples.device.model;

import home.samples.device.dto.Dht22DataCreateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Table(name="DHT22_DATA")
public class Dht22Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DTH22_DATA_ID", nullable = false)
    private Long Id;

    @Column(name = "TEMP", nullable = false)
    private Integer temp;

    @Column(name = "HUMIDITY", nullable = false)
    private Integer humidity;

    @Column(name = "CREATION_DATE", nullable = false)
    private Date creationDate;

    @Column(name="DEVICE_ID", nullable = false)
    private String deviceId;

    public Dht22Data(Dht22DataCreateDto createDto) {
        this.temp = createDto.getTemp();
        this.humidity=createDto.getHumidity();
        this.deviceId=createDto.getDeviceId();
        this.creationDate= new Date();
    }
}
