package home.samples.device.sqs;

import lombok.Data;

@Data
public class Dht22DataMsg {

    private Integer temp;
    private Integer hum;
}
