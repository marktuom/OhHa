package com.marktuom.shakki.domain;

import java.util.ArrayList;

/**
 * Kuvaa shakkilautaa. Sisältää pelitilanteen tutkimiseeen soveltuvia metodejä.
 * Pitää listaa kaikista ruuduista
 *
 * @author Markus
 */
public class Lauta {

    /**
     * Taulukko ruutuja jotka muodostavat 8x8 pelilaudan
     */
    private final Ruutu[][] ruudukko;
    
    /**
     * Vari joka kertoo kumman värisen nappulan siirtymävuoro on
     */
    private Vari vuorossa;
    
    
    /**
     * Luodaan uusi lauta ja siihen liittyvä ruudukko
     * Lisäksi asetetaan valkoinen pelaaja siirtymisvuoroon
     */
    public Lauta() {
        ruudukko = new Ruutu[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ruudukko[i][j] = new Ruutu(new Sijainti(j, i));
            }
        }
        this.vuorossa = Vari.VALKOINEN;
    }

    public Vari getVuorossa() {
        return vuorossa;
    }
    
/**
 * Luodaan ja asetetaan shakin nappulat niiden asianmukaisille aloituspaikoille ruudukossa
 */
    public void generoiNappulat() {
        for (int i = 0; i < 8; i++) {
            if (i == 0 || i == 7) {
                ruudukko[0][i].setNappula(new Torni(this, ruudukko[0][i], Vari.MUSTA));
                ruudukko[7][i].setNappula(new Torni(this, ruudukko[7][i], Vari.VALKOINEN));
            } else if (i == 1 || i == 6) {
                ruudukko[0][i].setNappula(new Ratsu(this, ruudukko[0][i], Vari.MUSTA));
                ruudukko[7][i].setNappula(new Ratsu(this, ruudukko[7][i], Vari.VALKOINEN));
            } else if (i == 2 || i == 5) {
                ruudukko[0][i].setNappula(new Lahetti(this, ruudukko[0][i], Vari.MUSTA));
                ruudukko[7][i].setNappula(new Lahetti(this, ruudukko[7][i], Vari.VALKOINEN));
            } else if (i == 3) {
                ruudukko[0][i].setNappula(new Kuningatar(this, ruudukko[0][i], Vari.MUSTA));
                ruudukko[7][i].setNappula(new Kuningatar(this, ruudukko[7][i], Vari.VALKOINEN));
            } else if (i == 4) {
                ruudukko[0][i].setNappula(new Kuningas(this, ruudukko[0][i], Vari.MUSTA));
                ruudukko[7][i].setNappula(new Kuningas(this, ruudukko[7][i], Vari.VALKOINEN));
            }

        }
        for (int i = 0; i < 8; i++) {
            ruudukko[1][i].setNappula(new Sotilas(this, ruudukko[1][i], Vari.MUSTA));
            ruudukko[6][i].setNappula(new Sotilas(this, ruudukko[6][i], Vari.VALKOINEN));
        }
    }

    public Ruutu[][] getRuudukko() {
        return ruudukko;
    }

    public Ruutu getRuutu(int x, int y) {
        try {
            return ruudukko[y][x];
        } catch (Exception e) {
            return null;
        }

    }

    
    /**
     * Siirtää laudalla olevan pelinappulan mikäli siirto on sääntöjen mukainen ja vaihtaa vuoron toiselle pelaajalle.
     * 
     * @param lahto Ruutu jossa olevaa nappulaa halutaan siirtää
     * @param kohde Ruutu johon lahtoruudusssa oleva nappula halutaan siirtää
     * @return Mikäli lahto sisältää nappulan ja se siirtyy ruutuun kohde palautetaan true. Muulloin palautetaan false;
     */
    public boolean siirra(Ruutu lahto, Ruutu kohde) {
        if (lahto == null || kohde == null) {
            return false;
        }

        if (lahto.getNappula() == null) {
            return false;
        }
        
        if(lahto.getNappula().getVari() != vuorossa){
            return false;
        }
        
        if(lahto.getNappula().liiku(kohde)){
            if(vuorossa == Vari.VALKOINEN){
                vuorossa = Vari.MUSTA;
            } else {
                vuorossa = Vari.VALKOINEN;
            }
            return true;
        }

        return false;
    }

    /**
     * Tutkii onko halutun värisen pelaajan kuningas shakissa
     * 
     * @param pelaaja Pelaajan väri jonka kuninkaan shakki tilanne halutaan tarkistaa
     * @return Palauttaa true mikäli kuningas on shakissa, muulloin false
     */
    public boolean shakissa(Vari pelaaja) {
        ArrayList<Ruutu> siirrot = new ArrayList<>();
        for (Ruutu[] rivi : ruudukko) {
            for (Ruutu ruutu : rivi) {
                if (ruutu.getNappula() != null) {
                    if (ruutu.getNappula().getVari() != pelaaja) {
                        for (Ruutu siirto : ruutu.getNappula().mahdollisetSiirrot()) {
                            siirrot.add(siirto);
                        }
                    }
                }
            }
        }
        return siirrot.contains(kuninkaanRuutu(pelaaja));
    }
   
    
    /**
     * Etsii ja palauttaa halutun pelaajan kuninkaan sisältävän ruudun
     * 
     * @param pelaaja   Pelaaja jonka kuningas halutaan etsiä
     * @return Ruutu jossa parametrina saadun pelaajan kuningas sijaitsee. Jos kuningasta ei löydy palautetaan null
     */
    public Ruutu kuninkaanRuutu(Vari pelaaja){
        for (Ruutu[] rivi : ruudukko) {
            for (Ruutu ruutu : rivi) {
                if (ruutu.getNappula() != null) {
                    if (ruutu.getNappula() instanceof Kuningas && ruutu.getNappula().getVari() == pelaaja) {
                        return ruutu;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Tutkii onko halutun värisen pelaajan kuningas matissa
     * 
     * @param pelaaja Pelaajan väri jonka kuninkaan matti tilanne halutaan tarkistaa
     * @return 
     */
    public boolean matissa(Vari pelaaja) {
        if (!shakissa(pelaaja)) {
            return false;
        }
        ArrayList<Ruutu> laillisetSiirrot = new ArrayList<>();
        for (Ruutu[] rivi : ruudukko) {
            for (Ruutu ruutu : rivi) {
                if (ruutu.getNappula() != null) {
                    if (ruutu.getNappula().getVari() == pelaaja) {
                        laillisetSiirrot.addAll(ruutu.getNappula().laillisetSiirrot());
                    }
                }
            }
        }

        return laillisetSiirrot.isEmpty();
    }

    /**
     * Tutkii onko kahden samalla linjalla olevien ruutujen välillä nappuloita
     * @param lahto Tutkittavan välin alku
     * @param kohde Tutkittavan välin loppu
     * @return Mikäli ruudut eivät ole linjassa tai niiden välillä ei ole nappuloita palautetaan false. Mikäli väliltä löytyy yksikin nappula palautetaan true;
     */
    public boolean nappuloitaTiella(Ruutu lahto, Ruutu kohde) {
        Sijainti alku = lahto.getSijainti();
        Sijainti maali = kohde.getSijainti();
        if (alku.xMuutos(maali) != 0 && alku.yMuutos(maali) != 0 && alku.xMuutos(maali) != alku.yMuutos(maali)) {
            return false;
        }
        if (alku.yErotus(maali) > 0) {

            if (alku.xErotus(maali) > 0) {
                for (int i = 1; i < alku.yMuutos(maali); i++) {
                    if (ruudukko[alku.getY() + i][alku.getX() + i].getNappula() != null) {
                        return true;
                    }
                }
            } else if (alku.xErotus(maali) < 0) {
                for (int i = 1; i < alku.yMuutos(maali); i++) {
                    if (ruudukko[alku.getY() + i][alku.getX() - i].getNappula() != null) {
                        return true;
                    }
                }
            } else {
                for (int i = 1; i < alku.yMuutos(maali); i++) {
                    if (ruudukko[alku.getY() + i][alku.getX()].getNappula() != null) {
                        return true;
                    }
                }
            }

        } else if (alku.yErotus(maali) < 0) {

            if (alku.xErotus(maali) > 0) {
                for (int i = 1; i < alku.yMuutos(maali); i++) {
                    if (ruudukko[alku.getY() - i][alku.getX() + i].getNappula() != null) {
                        return true;
                    }
                }
            } else if (alku.xErotus(maali) < 0) {
                for (int i = 1; i < alku.yMuutos(maali); i++) {
                    if (ruudukko[alku.getY() - i][alku.getX() - i].getNappula() != null) {
                        return true;
                    }
                }
            } else {
                for (int i = 1; i < alku.yMuutos(maali); i++) {
                    if (ruudukko[alku.getY() - i][alku.getX()].getNappula() != null) {
                        return true;
                    }
                }
            }

        } else {
            if (alku.xErotus(maali) < 0) {
                for (int i = 1; i < alku.xMuutos(maali); i++) {
                    if (ruudukko[alku.getY()][alku.getX() - i].getNappula() != null) {
                        return true;
                    }
                }
            } else if (alku.xErotus(maali) > 0) {
                for (int i = 1; i < alku.xMuutos(maali); i++) {
                    if (ruudukko[alku.getY()][alku.getX() + i].getNappula() != null) {
                        return true;
                    }
                }
            } else {
                return false;
            }
        }
        return false;
    }

}
