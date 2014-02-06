
package com.marktuom.shakki.domain;

/**
 * Kuvaa yhtä shakkilaudan ruutua. sisältää Sijainnin, nappulan sekä näihin liittyvät set ja get toiminnot.
 * @author Markus
 */
public class Ruutu {
    private Nappula nappula;
    private final Sijainti sijainti;
    
    
    public Ruutu(Sijainti sijainti, Nappula nappula) {
        this.sijainti = sijainti;
        this.nappula = nappula;
    }
    
     public Ruutu(Sijainti sijainti) {
        this(sijainti, null);
    }

    public Sijainti getSijainti() {
        return sijainti;
    }

   
    public Nappula getNappula() {
        return nappula;
    }

    public void setNappula(Nappula nappula) {
        this.nappula = nappula;
    }
    
    public void poistaNappula(){
        this.setNappula(null);
    }
    
    
}
