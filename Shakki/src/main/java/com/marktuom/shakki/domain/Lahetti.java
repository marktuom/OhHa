package com.marktuom.shakki.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Nappulan aliluokka joka kuvaa shakin lähettiä
 * @author Markus
 */
public class Lahetti extends Nappula {

    public Lahetti(Lauta lauta, Ruutu ruutu, Vari vari) {
        super(lauta, ruutu, vari);
    }

    @Override
    public boolean liiku(Ruutu kohde) {
        return super.liiku(kohde);
    }

    @Override
    public Collection<Ruutu> mahdollisetSiirrot() {
        ArrayList<Ruutu> mahdollisetKohteet = new ArrayList<>();
        
        mahdollisetKohteet.addAll(super.mahdollisetSiirrotSuuntaan(1, 1));
        mahdollisetKohteet.addAll(super.mahdollisetSiirrotSuuntaan(1, -1));
        mahdollisetKohteet.addAll(super.mahdollisetSiirrotSuuntaan(-1, 1));
        mahdollisetKohteet.addAll(super.mahdollisetSiirrotSuuntaan(-1, -1));
        
        return mahdollisetKohteet;
    }

}
