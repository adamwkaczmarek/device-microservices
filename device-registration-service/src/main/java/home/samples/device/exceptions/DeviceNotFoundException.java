package home.samples.device.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND,reason = "Cannot find device in data base")
public class DeviceNotFoundException extends RuntimeException{
}
