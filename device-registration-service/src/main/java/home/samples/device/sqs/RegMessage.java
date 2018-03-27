package home.samples.device.sqs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class RegMessage {

    private String deviceId;
    private String arnEndpoint;
    private String topic;
    private String deviceDesc;

    @SuppressWarnings("unchecked")
    @JsonProperty("state")
    private void unpackNested(Map<String,Object> state) {
        Map<String,String> reported = (Map<String,String>)state.get("reported");
           this.deviceId = reported.get("deviceId");
           this.arnEndpoint=reported.get("arnEndpoint");
           this.topic=reported.get("topic");
           this.deviceDesc=reported.get("deviceDesc");
    }

}
