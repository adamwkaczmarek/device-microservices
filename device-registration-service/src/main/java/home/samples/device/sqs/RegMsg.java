package home.samples.device.sqs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class RegMsg {

    private String deviceId;
    private DeviceDetailsMsg deviceDetails;

//    @SuppressWarnings("unchecked")
//    @JsonProperty("state")
//    private void unpackNested(Map<String,Object> state) {
//        Map<String,Object> reported = (Map<String,Object>)state.get("reported");
//           this.deviceId = (String)reported.get("deviceId");
//           Map<String,String> deviceDetails = (Map<String,String>)reported.get("deviceDetails");
//           this.arnEndpoint=deviceDetails.get("arnEndpoint");
//           this.topic=deviceDetails.get("topic");
//           this.deviceDesc=deviceDetails.get("deviceDesc");
//    }

}
