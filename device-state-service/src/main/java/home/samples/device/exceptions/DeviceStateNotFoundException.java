package home.samples.device.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Cannot find device state in database")
public class DeviceStateNotFoundException extends RuntimeException{
}
