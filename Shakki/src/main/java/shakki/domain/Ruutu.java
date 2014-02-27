package shakki.domain;

/**
 * Kuvaa yhtä shakkilaudan ruutua. sisältää Sijainnin, nappulan sekä näihin
 * liittyvät set ja get toiminnot.
 *
 * @author Markus
 */
public class Ruutu {

    /**
     * Ruudussa sijaitseva nappula
     */
    private Nappula nappula;

    /**
     * Ruudun sijainti laudan ruudukossa
     */
    private final Sijainti sijainti;

    /**
     * Luo ruudun jossa on nappula ja asettaa itsensä nappulan ruuduksi
     *
     * @param sijainti Ruudun sijainti shakkilaudalla
     * @param nappula Ruudussa sijaitseva nappula
     */
    public Ruutu(Sijainti sijainti, Nappula nappula) {
        this.sijainti = sijainti;
        this.nappula = nappula;
        if (nappula != null) {
            nappula.setRuutu(this);
        }
    }

    /**
     * Luo ruudun jossa ei ole nappulaa
     *
     * @param sijainti Ruudun sijainti shakkilaudalla
     */
    public Ruutu(Sijainti sijainti) {
        this(sijainti, null);
    }

    /**
     * Palauttaa sijaintinsa
     *
     * @return Ruutuun liittyvä sijainti
     */
    public Sijainti getSijainti() {
        return sijainti;
    }

    /**
     * Palauttaa nappulansa
     *
     * @return Ruutuun liittyv' nappula
     */
    public Nappula getNappula() {
        return nappula;
    }

    /**
     * Asettaa nappulan
     *
     * @param nappula Asetettava nappula
     */
    public void setNappula(Nappula nappula) {
        this.nappula = nappula;
    }

    /**
     * Poistaa ruudussa olevan nappulan eli asettaa nappulan arvoksi null
     */
    public void poistaNappula() {
        this.setNappula(null);
    }

}
