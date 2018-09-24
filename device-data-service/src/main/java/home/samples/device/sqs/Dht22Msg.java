package home.samples.device.sqs;


import lombok.Data;


@Data
public class Dht22Msg {

    private String deviceId;
    private  Dht22DataMsg deviceData;


}
