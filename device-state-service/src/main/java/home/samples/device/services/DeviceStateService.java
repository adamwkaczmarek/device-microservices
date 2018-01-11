package home.samples.device.services;


import home.samples.device.repository.DeviceStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DeviceStateService {

    @Autowired
    DeviceStateRepository deviceStateRepository;


}
