package arc.haldun.ik.applicationform.elements;

import java.io.Serializable;

public class BloodType implements Serializable {

    private String name, RH;

    public BloodType(String name, String RH) {
        this.name = name;
        this.RH = RH;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRH() {
        return RH;
    }

    public void setRH(String RH) {
        this.RH = RH;
    }


    @Override
    public String toString() {

        String classString;

        classString = this.name + "Rh " + RH;

        return classString;
    }

    public boolean isValid() {
        return !name.isEmpty() && !RH.isEmpty();
    }
}
