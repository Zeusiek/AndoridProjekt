package targonski.com.sklep;

import android.content.Context;

public class MySet {
    private final int cena;
    private final String opis;

    public MySet(String opis, int cena){
        this.cena = cena;
        this.opis = opis;
    }

    public int getCena() {
        return cena;
    }
    public String getOpis() {
        return opis;
    }

    @Override
    public String toString() {
        return opis + " - " + cena;
    }
}
