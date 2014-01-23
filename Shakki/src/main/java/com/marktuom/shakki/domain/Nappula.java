
package com.marktuom.shakki.domain;

/**
 *
 * @author Markus
 */
public abstract class Nappula {
    private final Vari vari;
    
    public abstract boolean voiLiikkua(Sijainti lahto, Sijainti kohde);
    
    public Nappula(Vari vari){
        this.vari = vari;
    }

    public Vari getVari() {
        return vari;
    }
    
    
}
