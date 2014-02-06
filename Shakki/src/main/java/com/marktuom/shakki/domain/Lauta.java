package com.marktuom.shakki.domain;

import java.util.ArrayList;

/**
 * Kuvaa shakkilautaa.
 * Sisältää pelitilanteen tutkimiseeen soveltuvia metodejä.
 * Pitää listaa kaikista ruuduista
 * @author Markus
 */
public class Lauta {

    private final Ruutu[][] ruudukko;

    public Lauta() {
        ruudukko = new Ruutu[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ruudukko[i][j] = new Ruutu(new Sijainti(j, i));
            }
        }
    }

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
                ruudukko[0][i].setNappula(new Kuningatar(this,  ruudukko[0][i], Vari.MUSTA));
                ruudukko[7][i].setNappula(new Kuningatar(this, ruudukko[7][i], Vari.VALKOINEN));
            } else if (i == 4) {
                ruudukko[0][i].setNappula(new Kuningas(this,ruudukko[0][i], Vari.MUSTA));
                ruudukko[7][i].setNappula(new Kuningas(this,ruudukko[7][i], Vari.VALKOINEN));
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

    public boolean siirra(Ruutu lahto, Ruutu kohde) {
        if (lahto == null || kohde == null) {
            return false;
        }
        
        if (lahto.getNappula() == null) {
            return false;
        }
        
        Nappula siirrettava = lahto.getNappula();
        Nappula poistettava = kohde.getNappula();    
        
        if(siirrettava.liiku(kohde)){
            if(shakissa(siirrettava.getVari())){
                lahto.setNappula(siirrettava);
                kohde.setNappula(poistettava);
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean shakissa(Vari pelaaja) {
        Ruutu kuninkaanRuutu = null;
        ArrayList<Ruutu> siirrot = new ArrayList<>();
        for (Ruutu[] rivi : ruudukko) {
            for (Ruutu ruutu : rivi) {
                if (ruutu.getNappula() != null) {
                    if (ruutu.getNappula() instanceof Kuningas && ruutu.getNappula().getVari() == pelaaja) {
                        kuninkaanRuutu = ruutu;
                    }
                    if (ruutu.getNappula().getVari() != pelaaja) {
                        for (Ruutu siirto : ruutu.getNappula().mahdollisetSiirrot()) {
                            siirrot.add(siirto);
                        }
                    }
                }
            }
        }
        return siirrot.contains(kuninkaanRuutu);
    }
    
    public boolean matissa(Vari pelaaja){
        if(!shakissa(pelaaja)){
            return false;
        }     
        for (Ruutu[] rivi : ruudukko) {
            for (Ruutu ruutu : rivi) {
                if (ruutu.getNappula() != null) {
                    if (ruutu.getNappula().getVari() == pelaaja) {
                        for (Ruutu siirto : ruutu.getNappula().mahdollisetSiirrot()) {
                            Nappula siirrettava = ruutu.getNappula();
                            Nappula poistettava = siirto.getNappula();
                            siirrettava.liiku(siirto);
                            if(!shakissa(pelaaja)){
                                siirrettava.setRuutu(ruutu);
                                poistettava.setRuutu(siirto);
                                return true;
                            }
                            siirrettava.setRuutu(ruutu);
                            poistettava.setRuutu(siirto);
                        }
                    }
                }
            }
        }
     
        return true;
    }

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
