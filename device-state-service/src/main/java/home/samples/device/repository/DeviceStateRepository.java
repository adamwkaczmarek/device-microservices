package home.samples.device.repository;

import home.samples.device.model.DeviceState;
import home.samples.device.model.DeviceStateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceStateRepository extends JpaRepository<DeviceState,DeviceStateId>{


    @Query("select u from DeviceState u where u.deviceStateId.deviceId = ?1")
    List<DeviceState> findByDeviceId(String deviceId);
}
