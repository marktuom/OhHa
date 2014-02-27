package shakki.domain;

import java.util.ArrayList;
import java.util.Collection;

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
     * Vuorossa olevan pelaajan väri
     */
    private int vuoro;

    /**
     * Luodaan uusi lauta ja siihen liittyvä ruudukko Lisäksi asetetaan
     * valkoinen pelaaja siirtymisvuoroon
     */
    public Lauta() {
        ruudukko = new Ruutu[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ruudukko[i][j] = new Ruutu(new Sijainti(j, i));
            }
        }
        this.vuorossa = Vari.VALKOINEN;
        this.vuoro = 1;
    }

    public Vari getVuorossa() {
        return vuorossa;
    }

    /**
     * Luodaan ja asetetaan shakin nappulat niiden asianmukaisille
     * aloituspaikoille ruudukossa
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

    /**
     * Palauttaa korrdinaattien määrittämän ruudun laudalta
     *
     * @param x Halutun ruudun x-koordinaatti
     * @param y Halutun ruudun y-koordinaatti
     * @return Koordinaateissa sijaitseva ruutu tai null jos ruutua ei löydy
     */
    public Ruutu getRuutu(int x, int y) {
        try {
            return ruudukko[y][x];
        } catch (Exception e) {
            return null;
        }

    }

    public int getVuoro() {
        return vuoro;
    }

    /**
     * Siirtää laudalla olevan pelinappulan mikäli siirto on sääntöjen mukainen
     * ja vaihtaa vuoron toiselle pelaajalle.
     *
     * @param lahto Ruutu jossa olevaa nappulaa halutaan siirtää
     * @param kohde Ruutu johon lahtoruudusssa oleva nappula halutaan siirtää
     * @return Mikäli lahto sisältää nappulan ja se siirtyy ruutuun kohde
     * palautetaan true. Muulloin palautetaan false;
     */
    public boolean siirra(Ruutu lahto, Ruutu kohde) {
        if (lahto == null || kohde == null) {
            return false;
        }

        if (lahto.getNappula() == null) {
            return false;
        }

        if (lahto.getNappula().getVari() != vuorossa) {
            return false;
        }

        if (lahto.getNappula().liiku(kohde)) {
            vuoro++;
            if (vuorossa == Vari.VALKOINEN) {
                vuorossa = Vari.MUSTA;
            } else {
                vuorossa = Vari.VALKOINEN;
            }
            return true;
        }

        return false;
    }

    /**
     * Tutkii onko parametrissa määrätyn värisen pelaajan kuningas shakissa
     *
     * @param pelaaja Pelaajan väri jonka kuninkaan shakki tilanne halutaan
     * tarkistaa
     * @return Palauttaa true mikäli kuningas on shakissa, muulloin false
     */
    public boolean shakissa(Vari pelaaja) {
        if (pelaaja == Vari.VALKOINEN) {
            return uhkaa(kuninkaanRuutu(pelaaja), Vari.MUSTA);
        } else {
            return uhkaa(kuninkaanRuutu(pelaaja), Vari.VALKOINEN);
        }
    }

    /**
     * Tutkii voiko parametrissa määrätyn värinen pelaaja liikkua määrättyyn
     * ruutuun
     *
     * @param kohde Ruutu johon siirtymistä tutkitaan
     * @param pelaaja Pelaaja jonka siirroista ruutua etsitään
     * @return True jos jokin nappula voi liikkua ruutuun, muulloin false
     */
    public boolean uhkaa(Ruutu kohde, Vari pelaaja) {
        ArrayList<Ruutu> siirrot = new ArrayList<>();
        for (Ruutu[] rivi : ruudukko) {
            for (Ruutu ruutu : rivi) {
                if (ruutu.getNappula() != null) {
                    if (ruutu.getNappula().getVari() == pelaaja) {
                        for (Ruutu siirto : ruutu.getNappula().mahdollisetSiirrot()) {
                            siirrot.add(siirto);
                        }
                    }
                }
            }
        }
        return siirrot.contains(kohde);
    }

    /**
     * Etsii ja palauttaa halutun pelaajan kuninkaan sisältävän ruudun
     *
     * @param pelaaja Pelaaja jonka kuningas halutaan etsiä
     * @return Ruutu jossa parametrina saadun pelaajan kuningas sijaitsee. Jos
     * kuningasta ei löydy palautetaan null
     */
    public Ruutu kuninkaanRuutu(Vari pelaaja) {
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
     * @param pelaaja Pelaajan väri jonka kuninkaan matti tilanne halutaan
     * tarkistaa
     * @return True jos kuningas on matissa, muulloin false
     */
    public boolean matissa(Vari pelaaja) {
        if (!shakissa(pelaaja)) {
            return false;
        }
        ArrayList<Ruutu> laillisetSiirrot = new ArrayList<>(pelaajanSiirrot(pelaaja));
        return laillisetSiirrot.isEmpty();
    }
    
    /**
     * Tutkii onko peli pattitilanteessa
     * @return true jos peli on pattitilanteessa, muulloin false;
     */
    public boolean tasapeli(){
         if (shakissa(vuorossa)) {
            return false;
        }
       ArrayList<Ruutu> laillisetSiirrot = new ArrayList<>(pelaajanSiirrot(vuorossa));
        return laillisetSiirrot.isEmpty();
    }
    
    /**
     * Palauttaa listan kaikista pelaajan laillisista siirroista
     * @param pelaaja Pelaaja jonka siirrot halutaan
     * @return Collection joka sisältää ruudut joihin pelaaja voi siirtää nappulansa
     */
    private Collection<Ruutu> pelaajanSiirrot(Vari pelaaja){
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
        return laillisetSiirrot;
    }

}
