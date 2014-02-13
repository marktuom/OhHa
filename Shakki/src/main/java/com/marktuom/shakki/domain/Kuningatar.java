package com.marktuom.shakki.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 *Nappulan aliluokka joka kuvaa shakin kuningatarta
 * @author Markus
 */
public class Kuningatar extends Nappula {

    public Kuningatar(Lauta lauta, Ruutu ruutu, Vari vari) {
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
        mahdollisetKohteet.addAll(super.mahdollisetSiirrotSuuntaan(1, 0));
        mahdollisetKohteet.addAll(super.mahdollisetSiirrotSuuntaan(1, -1));
        mahdollisetKohteet.addAll(super.mahdollisetSiirrotSuuntaan(0, 1));
        mahdollisetKohteet.addAll(super.mahdollisetSiirrotSuuntaan(0, -1));
        mahdollisetKohteet.addAll(super.mahdollisetSiirrotSuuntaan(-1, 1));
        mahdollisetKohteet.addAll(super.mahdollisetSiirrotSuuntaan(-1, 0));
        mahdollisetKohteet.addAll(super.mahdollisetSiirrotSuuntaan(-1, -1));
        
        return mahdollisetKohteet;
    }

}
