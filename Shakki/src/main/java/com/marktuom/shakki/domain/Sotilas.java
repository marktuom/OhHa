package com.marktuom.shakki.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Nappulan aliluokka joka kuvaa shakin sotilasta
 *
 * @author Markus
 */
public class Sotilas extends Nappula {

    private int ohestalyotavaVuorolla;

    public Sotilas(Lauta lauta, Ruutu ruutu, Vari vari) {
        super(lauta, ruutu, vari);
        this.ohestalyotavaVuorolla = -1;
    }

    public void kasvataSiirtoja() {
        siirtoja++;
    }

    @Override
    public boolean liiku(Ruutu kohde) {
        Ruutu ohestalyotava = null;
        if (this.ruutu.getSijainti().xMuutos(kohde.getSijainti()) != 0 && kohde.getNappula() == null) {
            ohestalyotava = lauta.getRuutu(kohde.getSijainti().getX(), this.ruutu.getSijainti().getY());
        }
        int vuoro = -1;
        if (kohde.getSijainti().yMuutos(this.ruutu.getSijainti()) == 2) {
            vuoro = lauta.getVuoro() + 1;
        }
        boolean liikkui = super.liiku(kohde);
        if (liikkui) {
            if ((this.ruutu.getSijainti().getY() == 0 && this.getVari() == Vari.VALKOINEN) || (this.ruutu.getSijainti().getY() == 7 && this.getVari() == Vari.MUSTA)) {
                Nappula ylennys = new Kuningatar(lauta, ruutu, this.getVari());
                ylennys.setRuutu(this.ruutu);
                this.ruutu.setNappula(ylennys);
            }
            if (ohestalyotava != null) {
                ohestalyotava.poistaNappula();
            }
            ohestalyotavaVuorolla = vuoro;
        }
        return liikkui;
    }

    @Override
    public Collection<Ruutu> mahdollisetSiirrot() {
        Sijainti omaSijainti = this.ruutu.getSijainti();
        ArrayList<Ruutu> mahdollisetKohteet = new ArrayList<>();

        //Siirto 1 eteen
        Ruutu mahdollinenKohde = lauta.getRuutu(omaSijainti.getX(), omaSijainti.getY() + this.getVari().getArvo());
        if (mahdollinenKohde != null && mahdollinenKohde.getNappula() == null) {
            mahdollisetKohteet.add(mahdollinenKohde);
        }

        //siirto 2 eteen
        mahdollinenKohde = lauta.getRuutu(omaSijainti.getX(), omaSijainti.getY() + (this.getVari().getArvo() * 2));
        if (this.siirtoja == 0 && mahdollinenKohde.getNappula() == null && lauta.getRuutu(omaSijainti.getX(), omaSijainti.getY() + this.getVari().getArvo()).getNappula() == null) {
            mahdollisetKohteet.add(mahdollinenKohde);
        }

        //oikea eteenpäin
        mahdollinenKohde = lauta.getRuutu(omaSijainti.getX() + 1, omaSijainti.getY() + this.getVari().getArvo());
        if (mahdollinenKohde != null) {
            //Syönti
            if (mahdollinenKohde.getNappula() != null && mahdollinenKohde.getNappula().getVari() != this.getVari()) {
                mahdollisetKohteet.add(mahdollinenKohde);
            }

            //ohestalyönti
            if (ohestalyonti(1)) {
                mahdollisetKohteet.add(mahdollinenKohde);
            }
        }

        //Vasen eteenpäin
        mahdollinenKohde = lauta.getRuutu(omaSijainti.getX() - 1, omaSijainti.getY() + this.getVari().getArvo());
        if (mahdollinenKohde != null) {
            //Syönti
            if (mahdollinenKohde.getNappula() != null && mahdollinenKohde.getNappula().getVari() != this.getVari()) {
                mahdollisetKohteet.add(mahdollinenKohde);
            }

            //Ohestalyönti
            if (ohestalyonti(-1)) {
                mahdollisetKohteet.add(mahdollinenKohde);
            }
        }

        return mahdollisetKohteet;
    }

    private Boolean ohestalyonti(int suunta) {
        Sijainti omaSijainti = this.ruutu.getSijainti();
        Ruutu ohestalyotava = lauta.getRuutu(omaSijainti.getX() + suunta, omaSijainti.getY());
        if (ohestalyotava != null) {
            if (ohestalyotava.getNappula() instanceof Sotilas && ohestalyotava.getNappula().getVari() != this.getVari() && ohestalyotava.getNappula().siirtoja == 1) {
                if (lauta.getVuoro() == ((Sotilas) ohestalyotava.getNappula()).getOhestalyotavaVuorolla()) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getOhestalyotavaVuorolla() {
        return ohestalyotavaVuorolla;
    }

}
