package home.samples.device.repository;

import home.samples.device.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  DeviceRepository extends JpaRepository<Device,String>{
}
