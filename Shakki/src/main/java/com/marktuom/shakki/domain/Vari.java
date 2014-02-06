
package com.marktuom.shakki.domain;

/**
 * Apuluokka jolla esitetään nappulan väriä
 * @author Markus
 */
public enum Vari {
    MUSTA(1), VALKOINEN(-1);
    
    private final int arvo;

    private Vari(int arvo) {
        this.arvo = arvo;
    }

    public int getArvo() {
        return arvo;
    }

}
