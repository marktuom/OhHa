package shakki.domain;

/**
 *
 * @author Markus
 */
public class Kuningatar extends Nappula {

    public Kuningatar(Vari vari) {
        super(vari);
    }

    @Override
    public boolean voiLiikkua(Sijainti lahto, Sijainti kohde) {
        int yMuutos = lahto.yMuutos(kohde);
        int xMuutos = lahto.xMuutos(kohde);
        if (xMuutos == 0 && yMuutos < 8) {
            return true;
        }
        if (yMuutos == 0 && xMuutos < 8) {
            return true;
        }
        return xMuutos == yMuutos;
    }

}
