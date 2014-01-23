/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shakki.domain;

/**
 *
 * @author Markus
 */
public class Ratsu extends Nappula {

    public Ratsu(Vari vari) {
        super(vari);
    }

    @Override
    public boolean voiLiikkua(Sijainti lahto, Sijainti kohde) {
        int yMuutos = lahto.yMuutos(kohde);
        int xMuutos = lahto.xMuutos(kohde);
        if (yMuutos == 2 && xMuutos == 1) {
            return true;
        }
        return yMuutos == 1 && xMuutos == 2;
    }

}
