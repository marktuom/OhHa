package com.marktuom.shakki.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Nappulan aliluokka joka kuvaa shakin kuningasta
 * @author Markus
 */
public class Kuningas extends Nappula {

    public Kuningas(Lauta lauta, Ruutu ruutu, Vari vari) {
        super(lauta, ruutu, vari);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public boolean liiku(Ruutu kohde) {   
        return super.liiku(kohde);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public Collection<Ruutu> mahdollisetSiirrot() {
        ArrayList<Ruutu> mahdollisetKohteet = new ArrayList<>();
        Sijainti omaSijainti = this.ruutu.getSijainti();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ruutu mahdollinenKohde = lauta.getRuutu(omaSijainti.getX() - 1 + i, omaSijainti.getY() - 1 + j);
                if(mahdollinenKohde != null && (mahdollinenKohde.getNappula() == null || mahdollinenKohde.getNappula().getVari() != this.getVari())){
                    mahdollisetKohteet.add(mahdollinenKohde);
                }
            }            
        }
        
        return mahdollisetKohteet;
    }

}
