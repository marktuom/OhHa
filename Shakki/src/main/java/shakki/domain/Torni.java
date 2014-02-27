package shakki.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Nappulan aliluokka joka kuvaa shakin tornia
 *
 * @author Markus
 */
public class Torni extends Nappula {

    /**
     * Luo uuden nappulan sekä asettaa itsensä sille annetun ruudun nappulaksi
     *
     * @param lauta Lauta jolla nappulan ruutu sijaitsee
     * @param ruutu Ruutu jossa nappulan halutaan sijaitsevan
     * @param vari Nappulan väri
     */
    public Torni(Lauta lauta, Ruutu ruutu, Vari vari) {
        super(lauta, ruutu, vari);
    }

    @Override
    public Collection<Ruutu> mahdollisetSiirrot() {
        ArrayList<Ruutu> mahdollisetKohteet = new ArrayList<>();

        mahdollisetKohteet.addAll(super.mahdollisetSiirrotSuuntaan(1, 0));
        mahdollisetKohteet.addAll(super.mahdollisetSiirrotSuuntaan(0, 1));
        mahdollisetKohteet.addAll(super.mahdollisetSiirrotSuuntaan(0, -1));
        mahdollisetKohteet.addAll(super.mahdollisetSiirrotSuuntaan(-1, 0));

        return mahdollisetKohteet;
    }

}
