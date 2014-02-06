
package com.marktuom.shakki.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Nappulan aliluokka joka kuvaa shakin ratsua.
 * @author Markus
 */
public class Ratsu extends Nappula {

    public Ratsu(Lauta lauta, Ruutu ruutu, Vari vari) {
        super(lauta, ruutu, vari);
    }

    @Override
    public boolean liiku(Ruutu kohde) {
        return super.liiku(kohde);
    }

    @Override
    public Collection<Ruutu> mahdollisetSiirrot() {
        Sijainti omaSijainti = this.ruutu.getSijainti();
        ArrayList<Ruutu> mahdollisetKohteet = new ArrayList<>();
        int[][] siirrot = {
            {-2, 1},
            {-1, 2},
            {1, 2},
            {2, 1},
            {2, -1},
            {1, -2},
            {-1, -2},
            {-2, -1}
        };
        for (int[] is : siirrot) {
            Ruutu mahdollinenKohde = lauta.getRuutu(omaSijainti.getX() + is[0], omaSijainti.getY() + is[1]);
            if (mahdollinenKohde != null && (mahdollinenKohde.getNappula() == null || mahdollinenKohde.getNappula().getVari() != this.getVari())) {
                mahdollisetKohteet.add(mahdollinenKohde);
            }
        }
        
        return mahdollisetKohteet;
    }

}
