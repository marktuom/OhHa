package com.marktuom.shakki.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Nappulan aliluokka joka kuvaa shakin tornia
 *
 * @author Markus
 */
public class Torni extends Nappula {

    public Torni(Lauta lauta, Ruutu ruutu, Vari vari) {
        super(lauta, ruutu, vari);
    }

    @Override
    public boolean liiku(Ruutu kohde) {
        return super.liiku(kohde);
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
