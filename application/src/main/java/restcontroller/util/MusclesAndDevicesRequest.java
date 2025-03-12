package restcontroller.util;

import entitys.Device;
import entitys.Muscle;

import java.util.List;

public class MusclesAndDevicesRequest {

    private List<Muscle> muscles;
    private List<Device> devices;

    public List<Muscle> getMuscles() {
        return muscles;
    }

    public void setMuscles(List<Muscle> muscles) {
        this.muscles = muscles;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
