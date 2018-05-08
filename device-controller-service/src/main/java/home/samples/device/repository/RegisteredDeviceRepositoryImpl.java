package home.samples.device.repository;

import home.samples.device.dto.DeviceDto;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
@NoArgsConstructor
public class RegisteredDeviceRepositoryImpl implements RegisteredDeviceRepository {

    private static final String HASH_NAME = "registered_device";
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;


    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }


    @Autowired
    public RegisteredDeviceRepositoryImpl(
        RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void saveDevice(DeviceDto device) {
        hashOperations.put(HASH_NAME, device.getDeviceId(), device);

    }

    @Override
    public void deleteDevice(String deviceId) {
        hashOperations.delete(HASH_NAME, deviceId);

    }

    @Override
    public DeviceDto findDevice(String deviceId) {
        return (DeviceDto) hashOperations.get(HASH_NAME, deviceId);
    }
}
