package shakki.domain;

/**
 * Apuluokka jolla esitetään ja lasketaan sijainteja laudalla
 * @author Markus
 */
public class Sijainti {

    /**
     * X-koordinaatti
     */
    private int x;
    
    /**
     * Y-koordinaatti
     */
    private int y;

    /**
     * 
     * @param x X-koordinaatti
     * @param y Y-koordinaatti
     */
    public Sijainti(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Palauttaa X-koordinaatin
     * @return X-koordinaatti
     */
    public int getX() {
        return x;
    }

    /**
     * Palauttaa Y-koordinaatin
     * @return Y-koordinaatti
     */
    public int getY() {
        return y;
    }

    /**
     * Laskee parametrina saadun sijainnin ja omien koordinaattiensa erotuksen
     * @param sijainti Sijainti jonka koordinaattien arvot tämän omista erotetaan
     * @return Erotuksen perusteella luotu sijainti
     */
    public Sijainti erotus(Sijainti sijainti){
        return new Sijainti(this.xErotus(sijainti), this.yErotus(sijainti));
    }
    
    /**
     * Laskee parametrina saadun sijainnin ja omien X-koordinaattien erotuksen
     * @param piste Sijainti jonka X-koordinaattien arvot tämän omista erotetaan
     * @return Erotuksen arvo
     */
    public int xErotus(Sijainti piste){
        return piste.getX() - this.x;
    }
    
     /**
     * Laskee parametrina saadun sijainnin ja omien Y-koordinaattien erotuksen
     * @param piste Sijainti jonka Y-koordinaattien arvot tämän omista erotetaan
     * @return Erotuksen arvo
     */
    public int yErotus(Sijainti piste){
        return piste.getY() - this.y;
    }
    
    /**
     * Laskee parametrina saadun sijainnin ja omien X-koordinaattien erotuksen itseisarvon
     * @param piste Sijainti jonka X-koordinaattien arvot tämän omista erotetaan
     * @return Erotuksen itseisarvon arvo
     */
      public int xMuutos(Sijainti piste){
        return Math.abs(this.xErotus(piste));
    }
    
       /**
     * Laskee parametrina saadun sijainnin ja omien Y-koordinaattien erotuksen itseisarvon
     * @param piste Sijainti jonka Y-koordinaattien arvot tämän omista erotetaan
     * @return Erotuksen itseisarvon arvo
     */
    public int yMuutos(Sijainti piste){
        return Math.abs(this.yErotus(piste));
    }
}
