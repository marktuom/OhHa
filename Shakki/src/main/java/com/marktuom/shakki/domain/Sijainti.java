package com.marktuom.shakki.domain;

/**
 * Apuluokka jolla esitetään ja lasketaan sijainteja laudalla
 * @author Markus
 */
public class Sijainti {

    private int x;
    private int y;

    public Sijainti(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    
    public Sijainti erotus(Sijainti piste){
        return new Sijainti(this.xErotus(piste), this.yErotus(piste));
    }
    
    public int xErotus(Sijainti piste){
        return piste.getX() - this.x;
    }
    
    public int yErotus(Sijainti piste){
        return piste.getY() - this.y;
    }
    
      public int xMuutos(Sijainti piste){
        return Math.abs(this.xErotus(piste));
    }
    
    public int yMuutos(Sijainti piste){
        return Math.abs(this.yErotus(piste));
    }
}
