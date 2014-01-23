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
