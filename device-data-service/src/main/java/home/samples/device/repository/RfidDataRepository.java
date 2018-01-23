package home.samples.device.repository;

import home.samples.device.model.RfidData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RfidDataRepository extends JpaRepository<RfidData,Long>{

    List<RfidData> findByDeviceId(String deviceId);
}
