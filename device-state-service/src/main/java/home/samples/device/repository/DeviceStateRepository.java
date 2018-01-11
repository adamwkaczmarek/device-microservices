package home.samples.device.repository;

import home.samples.device.model.DeviceState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceStateRepository extends JpaRepository<DeviceState,String>{
}
