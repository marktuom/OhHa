package com.marktuom.shakki.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Shakkinappuloiden yliluokka. Määrittää kaikille nappuloille yhteiset
 * liikkumiseen liittyvät toiminnot sekä useille nappuloille yhteiset muuttujat.
 *
 * @author Markus
 *
 */
public abstract class Nappula {

    private final Vari vari;
    protected final Lauta lauta;
    protected Ruutu ruutu;
    
    /**
     * Sotilas, Torni ja Kuningas tarvitsevat tätä erikoissiirtojensa ehdoissa
     */
    protected int siirtoja;

    public void setRuutu(Ruutu ruutu) {
        this.ruutu = ruutu;
    }

    public int getSiirtoja() {
        return siirtoja;
    }

    /**
     *
     * @param lauta Lauta jolla nappulan ruutu sijaitsee
     * @param ruutu Ruutu jossa nappulan halutaan sijaitsevan
     * @param vari Nappulan väri
     */
    public Nappula(Lauta lauta, Ruutu ruutu, Vari vari) {
        this.vari = vari;
        this.lauta = lauta;
        this.ruutu = ruutu;
        this.siirtoja = 0;
        if (this.ruutu != null) {
            this.ruutu.setNappula(this);
        }
    }

    /**
     * Siirtää nappulan parametrin määrittämään ruutuun
     *
     * @param kohde Ruutu johon nappula halutaan siirtää
     * @return Palauttaa true mikäli siirto onnistuu, muulloin false
     *
     */
    public boolean liiku(Ruutu kohde) {
        if (!this.laillisetSiirrot().contains(kohde)) {
            return false;
        }
        this.siirtoja++;
        this.ruutu.poistaNappula();
        this.ruutu = kohde;
        this.ruutu.setNappula(this);
        return true;
    }

    /**
     * Tutkii mihin ruutuihin nappula voi liikkua sen omien liikkumissääntöjen
     * perusteella
     *
     * @return Palauttaa Collectionin ruuduista joihin nappulan on mahdollista
     * siirtyä omien sääntöjensä mukaan
     *
     */
    public abstract Collection<Ruutu> mahdollisetSiirrot();

    /**
     * Tutkii mihin ruutuihin nappula voi liikkua shakki tilanteet huomioiden
     *
     * @return Palauttaa Collectionin ruuduista joihin nappulan voidaan siirtää
     *
     */
    public Collection<Ruutu> laillisetSiirrot() {
        ArrayList<Ruutu> siirrot = new ArrayList<>(this.mahdollisetSiirrot());
        ArrayList<Ruutu> laittomat = new ArrayList<>();
        for (Ruutu ruutu1 : siirrot) {

            Ruutu lahto = this.ruutu;
            Nappula poistettava = ruutu1.getNappula();

            this.ruutu.poistaNappula();
            this.ruutu = ruutu1;
            this.ruutu.setNappula(this);

            if (lauta.shakissa(this.vari)) {
                laittomat.add(ruutu1);
            }

            this.ruutu = lahto;
            this.ruutu.setNappula(this);
            if (poistettava != null) {
                poistettava.setRuutu(ruutu1);
            }
            ruutu1.setNappula(poistettava);
        }
        siirrot.removeAll(laittomat);
        return siirrot;
    }

    /**
     * Palauttaa listan vapaista ruuduista määrätyllä linjalla
     * @param xMuutos 1, 0 tai -1 sen mukaan mihin suuntaan siirrot halutaan tutkia x-akselin suhteen
     * @param Ymuutos 1, 0 tai -1 sen mukaan mihin suuntaan siirrot halutaan tutkia y-akselin suhteen
     * @return 
     */
    protected Collection<Ruutu> mahdollisetSiirrotSuuntaan(int xMuutos, int Ymuutos) {
        if(Math.abs(xMuutos) > 1 || Math.abs(Ymuutos) > 1){
            return null;
        }
        ArrayList<Ruutu> siirrot = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            Ruutu mahdollinenKohde = lauta.getRuutu(this.ruutu.getSijainti().getX() + (i*xMuutos), this.ruutu.getSijainti().getY() + (i*Ymuutos));
            if (mahdollinenKohde == null || (mahdollinenKohde.getNappula() != null && mahdollinenKohde.getNappula().getVari() == this.getVari())) {
                break;
            }
            if (mahdollinenKohde.getNappula() != null && mahdollinenKohde.getNappula().getVari() != this.getVari()) {
                siirrot.add(mahdollinenKohde);
                break;
            }
            siirrot.add(mahdollinenKohde);
        }
        return siirrot;
    }

    public Vari getVari() {
        return vari;
    }

}
