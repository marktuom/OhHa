package com.marktuom.shakki.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Nappulan aliluokka joka kuvaa shakin kuningasta
 *
 * @author Markus
 */
public class Kuningas extends Nappula {

    /**
     * Luo uuden nappulan sekä asettaa itsensä sille annetun ruudun nappulaksi
     *
     * @param lauta Lauta jolla nappulan ruutu sijaitsee
     * @param ruutu Ruutu jossa nappulan halutaan sijaitsevan
     * @param vari Nappulan väri
     */
    public Kuningas(Lauta lauta, Ruutu ruutu, Vari vari) {
        super(lauta, ruutu, vari);

    }

    /**
     * {@inheritDoc} Perustoiminnon lisäksi siirtää tornin tornituksessa
     *
     * @param kohde {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean liiku(Ruutu kohde) {
        Ruutu torninRuutu = null;
        int xErotus = kohde.getSijainti().xMuutos(ruutu.getSijainti());
        if (Math.abs(xErotus) == 2) {
            int torninX;
            if (xErotus == 2) {
                torninX = 7;
            } else {
                torninX = 0;
            }
            torninRuutu = lauta.getRuutu(torninX, this.ruutu.getSijainti().getY());
        }

        boolean liikkui = super.liiku(kohde);

        if (torninRuutu != null) {
            int suunta;
            if (this.ruutu.getSijainti().getX() == 6) {
                suunta = -1;
            } else {
                suunta = 1;
            }
            torninRuutu.getNappula().KasvataSiirtoja();
            asetaRuutuun(torninRuutu, lauta.getRuutu(this.ruutu.getSijainti().getX() + suunta, this.ruutu.getSijainti().getY()));
        }
        return liikkui;
    }

 
    @Override
    public Collection<Ruutu> mahdollisetSiirrot() {
        ArrayList<Ruutu> mahdollisetKohteet = new ArrayList<>();
        Sijainti omaSijainti = this.ruutu.getSijainti();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ruutu mahdollinenKohde = lauta.getRuutu(omaSijainti.getX() - 1 + i, omaSijainti.getY() - 1 + j);
                if (mahdollinenKohde != null && (mahdollinenKohde.getNappula() == null || mahdollinenKohde.getNappula().getVari() != this.getVari())) {
                    mahdollisetKohteet.add(mahdollinenKohde);
                }
            }
        }
        if (voiMuurata(1)) {
            mahdollisetKohteet.add(lauta.getRuutu(omaSijainti.getX() + 2, omaSijainti.getY()));
        }
        if (voiMuurata(-1)) {
            mahdollisetKohteet.add(lauta.getRuutu(omaSijainti.getX() - 2, omaSijainti.getY()));
        }
        return mahdollisetKohteet;
    }

    /**
     * Tutkii onko muuraus mahdollinen huomioimatta uhkaus ja shakki tilanteita
     *
     * @param suunta 1 tai -1 riippuen kumpaan suuntaan tehtävä muuraus
     * tutkitaan
     * @return True jos voi muurata haluttuun suuntaan, muulloin false
     */
    private boolean voiMuurata(int suunta) {
        Sijainti omaSijainti = this.ruutu.getSijainti();
        if (omaSijainti.getX() != 4) {
            return false;
        }
        if (Math.abs(suunta) != 1) {
            return false;
        }
        if (this.siirtoja != 0) {
            return false;
        }
        int x = 2;
        if (suunta == -1) {
            x++;
        }
        for (int i = 1; i <= x; i++) {
            if (lauta.getRuutu(omaSijainti.getX() + i * suunta, omaSijainti.getY()).getNappula() != null) {
                return false;
            }
        }
        if (lauta.getRuutu(omaSijainti.getX() + (x + 1) * suunta, omaSijainti.getY()).getNappula() == null) {
            return false;
        }
        if (lauta.getRuutu(omaSijainti.getX() + (x + 1) * suunta, omaSijainti.getY()).getNappula().getSiirtoja() != 0) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc} Huomioi myös muurausta koskevat erikoissäännöt
     *
     * @return {@inheritDoc}
     */
    @Override
    public Collection<Ruutu> laillisetSiirrot() {
        Sijainti omaSijainti = this.ruutu.getSijainti();
        ArrayList<Ruutu> siirrot = new ArrayList<>(super.laillisetSiirrot());
        if (lauta.shakissa(this.getVari())) {
            siirrot.remove(lauta.getRuutu(omaSijainti.getX() + 2, omaSijainti.getY()));
            siirrot.remove(lauta.getRuutu(omaSijainti.getX() - 2, omaSijainti.getY()));
        } else {
            Vari vastustaja;
            if (this.getVari() == Vari.VALKOINEN) {
                vastustaja = Vari.MUSTA;
            } else {
                vastustaja = Vari.VALKOINEN;
            }
            if (lauta.uhkaa(lauta.getRuutu(omaSijainti.getX() + 1, omaSijainti.getY()), vastustaja)) {
                siirrot.remove(lauta.getRuutu(omaSijainti.getX() + 2, omaSijainti.getY()));
            }
            if (lauta.uhkaa(lauta.getRuutu(omaSijainti.getX() - 1, omaSijainti.getY()), vastustaja)) {
                siirrot.remove(lauta.getRuutu(omaSijainti.getX() - 2, omaSijainti.getY()));
            }
        }

        return siirrot;
    }
}
