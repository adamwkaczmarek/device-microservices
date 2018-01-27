package home.samples.device.repository;

import home.samples.device.model.Dht22Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Dht22DataRepository extends JpaRepository<Dht22Data,Long>{

    List<Dht22Data> findByDeviceId(String deviceId);
}
