
package shakki.domain;

/**
 *
 * @author Markus
 */
public class Lahetti extends Nappula{

    public Lahetti(Vari vari) {
        super(vari);
    }

    @Override
    public boolean voiLiikkua(Sijainti lahto, Sijainti kohde) {
       int yMuutos = lahto.yMuutos(kohde);
       int xMuutos = lahto.xMuutos(kohde);
       return xMuutos == yMuutos;
    }
    
}
