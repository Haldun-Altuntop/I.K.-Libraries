package arc.haldun.ik.applicationform.elements;

import java.io.Serializable;

public enum Currency implements Serializable {
    TL(0),
    DOLLAR(1);

    private int id;

    Currency(int id) {
        this.id = id;
    }

    public static Currency findById(int id) {

        Currency[] currencies = Currency.values();

        for (Currency c : currencies) {
            if (c.id == id) return c;
        }
        throw new IllegalArgumentException("Geçersiz id:" + id);
    }
}
