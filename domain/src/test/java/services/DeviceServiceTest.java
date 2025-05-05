package services;

import entitys.Device;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.DeviceRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    private Device device;

    @BeforeEach
    void setUp() {
        device = new Device();
        device.setId(1L);
        device.setName("Treadmill");
    }

    @Test
    void testGetAllDevices() {
        when(deviceRepository.findAll()).thenReturn(Arrays.asList(device));

        List<Device> devices = deviceService.getAllDevices();

        assertNotNull(devices);
        assertFalse(devices.isEmpty());
        assertEquals(1, devices.size());
        assertEquals(device.getName(), devices.get(0).getName());
        verify(deviceRepository, times(1)).findAll();
    }
}
