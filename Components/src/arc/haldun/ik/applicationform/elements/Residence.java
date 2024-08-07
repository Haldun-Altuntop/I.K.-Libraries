package arc.haldun.ik.applicationform.elements;

import java.io.Serializable;

public enum Residence implements Serializable {
    RENT(0),
    POSSESSION(1),
    FAMILY(2);

    private final int i;

    Residence(int i) {
        this.i = i;
    }

    public int getId() {
        return i;
    }

    public static Residence findById(int id) {

        Residence[] residences = Residence.values();

        for (Residence r : residences) {
            if (r.getId() == id) return r;
        }

        throw new IllegalArgumentException("Geçersiz numara: " + id);
    }
}