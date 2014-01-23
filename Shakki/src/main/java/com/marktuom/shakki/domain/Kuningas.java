package com.marktuom.shakki.domain;

/**
 *
 * @author Markus
 */
public class Kuningas extends Nappula {

    public Kuningas(Vari vari) {
        super(vari);
    }

    @Override
    public boolean voiLiikkua(Sijainti lahto, Sijainti kohde) {   
        return (lahto.xMuutos(kohde) <= 1 && lahto.yMuutos(kohde) <= 1);
    }

}
