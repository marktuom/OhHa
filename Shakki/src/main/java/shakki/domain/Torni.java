package shakki.domain;

/**
 *
 * @author Markus
 */
public class Torni extends Nappula {

    public Torni(Vari vari) {
        super(vari);
    }

    @Override
    public boolean voiLiikkua(Sijainti lahto, Sijainti kohde) {
        int yMuutos = lahto.yMuutos(kohde);
        int xMuutos = lahto.xMuutos(kohde);
        if (xMuutos == 0 && yMuutos < 8) {
            return true;
        }
        return yMuutos == 0 && xMuutos < 8;
    }

}
