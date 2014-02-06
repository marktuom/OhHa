package com.marktuom.shakki.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Nappulan aliluokka joka kuvaa shakin tornia
 * @author Markus
 */
public class Torni extends Nappula {

    public Torni(Lauta lauta, Ruutu ruutu, Vari vari) {
        super(lauta, ruutu, vari);
    }

    @Override
    public boolean liiku(Ruutu kohde) {
        return super.liiku(ruutu);
    }

    @Override
    public Collection<Ruutu> mahdollisetSiirrot() {
        ArrayList<Ruutu> mahdollisetKohteet = new ArrayList<>();
        Sijainti omaSijainti = this.ruutu.getSijainti();
        for (int i = 0; i < 7; i++) {
            Ruutu mahdollinenKohde = lauta.getRuutu(omaSijainti.getX() + 1 + i, omaSijainti.getY());
            if (mahdollinenKohde == null || (mahdollinenKohde.getNappula() != null && mahdollinenKohde.getNappula().getVari() == this.getVari())) {
                break;
            }
            if (mahdollinenKohde.getNappula() != null && mahdollinenKohde.getNappula().getVari() != this.getVari()) {
                mahdollisetKohteet.add(mahdollinenKohde);
                break;
            }
            mahdollisetKohteet.add(mahdollinenKohde);
        }
        for (int i = 0; i < 7; i++) {
            Ruutu mahdollinenKohde = lauta.getRuutu(omaSijainti.getX() - 1 - i, omaSijainti.getY());
            if (mahdollinenKohde == null || (mahdollinenKohde.getNappula() != null && mahdollinenKohde.getNappula().getVari() == this.getVari())) {
                break;
            }
            if (mahdollinenKohde.getNappula() != null && mahdollinenKohde.getNappula().getVari() != this.getVari()) {
                mahdollisetKohteet.add(mahdollinenKohde);
                break;
            }
            mahdollisetKohteet.add(mahdollinenKohde);
        }
        for (int i = 0; i < 7; i++) {
            Ruutu mahdollinenKohde = lauta.getRuutu(omaSijainti.getX(), omaSijainti.getY() + 1 + i);
            if (mahdollinenKohde == null || (mahdollinenKohde.getNappula() != null && mahdollinenKohde.getNappula().getVari() == this.getVari())) {
                break;
            }
            if (mahdollinenKohde.getNappula() != null && mahdollinenKohde.getNappula().getVari() != this.getVari()) {
                mahdollisetKohteet.add(mahdollinenKohde);
                break;
            }
            mahdollisetKohteet.add(mahdollinenKohde);
        }
        for (int i = 0; i < 7; i++) {
            Ruutu mahdollinenKohde = lauta.getRuutu(omaSijainti.getX(), omaSijainti.getY() - 1 - i);
            if (mahdollinenKohde == null || (mahdollinenKohde.getNappula() != null && mahdollinenKohde.getNappula().getVari() == this.getVari())) {
                break;
            }
            if (mahdollinenKohde.getNappula() != null && mahdollinenKohde.getNappula().getVari() != this.getVari()) {
                mahdollisetKohteet.add(mahdollinenKohde);
                break;
            }
            mahdollisetKohteet.add(mahdollinenKohde);
        }
        return mahdollisetKohteet;
    }

}
