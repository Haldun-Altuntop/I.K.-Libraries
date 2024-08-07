package arc.haldun.ik.applicationform.elements;

import java.io.Serializable;

public class Children implements Serializable {

    private int son, daughter;

    public Children(int son, int daughter) {
        this.son = son;
        this.daughter = daughter;
    }

    @Override
    public String toString() {

        String classString;

        classString = "Kız: " + daughter + " " +
                    "Erkek: " + son;

        return classString;
    }

    public boolean isValid() {
        return true;
    }
}
