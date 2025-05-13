package services;

import entitys.Device;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.DeviceRepository;
import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device addDevice(Device device) {
        return deviceRepository.save(device);
    }

    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }

    public Device updateDevice(Long id, Device updatedDevice) {
        if (!deviceRepository.existsById(id)) {
            throw new RuntimeException("Device not found");
        }
        updatedDevice.setId(id);
        return deviceRepository.save(updatedDevice);
    }
}
