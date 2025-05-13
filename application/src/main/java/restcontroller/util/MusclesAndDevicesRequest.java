package restcontroller.util;

import entitys.Device;
import entitys.Muscle;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MusclesAndDevicesRequest {
    private List<Muscle> muscles;
    private List<Device> devices;
}
