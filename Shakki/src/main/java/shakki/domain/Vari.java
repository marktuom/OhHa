
package shakki.domain;

/**
 * Apuluokka jolla esitetään nappulan väriä ja pelaajaa
 * @author Markus
 */
public enum Vari {
    MUSTA(1), VALKOINEN(-1);
    
    /**
     * Väriä vastaava lukuarvo, mustalla 1 ja valkoisella -1
     */
    private final int arvo;

    /**
     * Luo värin johon liittyy lukuarvo
     * @param arvo Väriin liitettävä arvo
     */
    private Vari(int arvo) {
        this.arvo = arvo;
    }

    /**
     * Palauttaa väriin liittyvän arvon
     * @return Lukuarvo
     */
    public int getArvo() {
        return arvo;
    }

}
